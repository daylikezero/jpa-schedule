package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.domain.dto.request.ScheduleRequestDto;
import com.example.jpaschedule.domain.dto.response.ScheduleResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.entity.Schedule;
import com.example.jpaschedule.domain.repository.ScheduleRepository;
import com.example.jpaschedule.config.context.MemberContext;
import com.example.jpaschedule.common.util.EmptyTool;
import com.example.jpaschedule.exception.CustomException;
import com.example.jpaschedule.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberService memberService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(String title, String contents) {
        Member findMember = memberService.findMember(MemberContext.getMemberId());
        Schedule schedule = new Schedule(title, contents, findMember);
        scheduleRepository.save(schedule);
        return ScheduleResponseDto.fromSchedule(schedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAllByMember_Id(MemberContext.getMemberId());
        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleResponseDtos.add(ScheduleResponseDto.fromSchedule(schedule));
        }
        return scheduleResponseDtos;
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = findSchedule(id);
        return ScheduleResponseDto.fromSchedule(findSchedule);
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto) {
        Schedule schedule = findSchedule(id);
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
        scheduleRepository.delete(schedule);
    }

    private Schedule findSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
        if (EmptyTool.notEmpty(schedule.getDeletedAt())) {
            throw new CustomException(ErrorCode.ENTITY_DELETED, String.valueOf(id));
        }
        if (!schedule.getMember().getId().equals(MemberContext.getMemberId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, "로그인 ID와 작성자 ID가 일치하지 않습니다.");
        }
        return schedule;
    }
}
