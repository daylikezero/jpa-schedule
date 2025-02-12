package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.common.util.EmptyTool;
import com.example.jpaschedule.config.context.MemberContext;
import com.example.jpaschedule.domain.dto.request.ScheduleRequestDto;
import com.example.jpaschedule.domain.dto.response.ScheduleResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.entity.Schedule;
import com.example.jpaschedule.domain.repository.ScheduleRepository;
import com.example.jpaschedule.domain.repository.ScheduleRepositoryCustom;
import com.example.jpaschedule.exception.CustomException;
import com.example.jpaschedule.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberService memberService;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleRepositoryCustom scheduleRepositoryCustom;

    @Transactional
    public ScheduleResponseDto create(String title, String contents) {
        Member findMember = memberService.findMember(MemberContext.getMemberId());
        Schedule schedule = scheduleRepository.save(Schedule.of(title, contents, findMember));
        return ScheduleResponseDto.fromSchedule(schedule);
    }

    @Transactional(readOnly = true)
    public Page<ScheduleResponseDto> findAll(int page, int size, ScheduleRequestDto dto) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("updatedAt").descending());
        Page<Schedule> schedules = scheduleRepositoryCustom.findDynamicSchedules(dto, pageable);
        return schedules.map(ScheduleResponseDto::fromSchedule);
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        return ScheduleResponseDto.fromSchedule(findSchedule(id));
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto) {
        Schedule schedule = findSchedule(id);
        validScheduleMemberId(schedule);
        if (EmptyTool.notEmpty(dto.getTitle())) {
            schedule.updateTitle(dto.getTitle());
        }
        if (EmptyTool.notEmpty(dto.getContents())) {
            schedule.updateContents(dto.getContents());
        }
        return ScheduleResponseDto.fromSchedule(schedule);
    }

    @Transactional
    public void delete(Long id) {
        Schedule schedule = findSchedule(id);
        validScheduleMemberId(schedule);
        scheduleRepository.delete(schedule);
    }

    public Schedule findSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
        if (EmptyTool.notEmpty(schedule.getDeletedAt())) {
            throw new CustomException(ErrorCode.ENTITY_DELETED, String.valueOf(id));
        }
        return schedule;
    }

    private void validScheduleMemberId(Schedule schedule) {
        if (!schedule.getMember().getId().equals(MemberContext.getMemberId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, "로그인 ID와 작성자 ID가 일치하지 않습니다.");
        }
    }
}
