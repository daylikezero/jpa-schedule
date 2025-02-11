package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.domain.dto.request.ScheduleRequestDto;
import com.example.jpaschedule.domain.dto.response.ScheduleResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.entity.Schedule;
import com.example.jpaschedule.domain.repository.ScheduleRepository;
import com.example.jpaschedule.config.context.MemberContext;
import com.example.jpaschedule.common.util.EmptyTool;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberService memberService;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents) {
        Member findMember = memberService.getMember(MemberContext.getMemberId());
        Schedule schedule = new Schedule(title, contents, findMember);
        scheduleRepository.save(schedule);
        return ScheduleResponseDto.fromSchedule(schedule);
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAllByMember_Id(MemberContext.getMemberId());
        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleResponseDtos.add(ScheduleResponseDto.fromSchedule(schedule));
        }
        return scheduleResponseDtos;
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = getSchedule(id);
        return ScheduleResponseDto.fromSchedule(findSchedule);
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto) {
        Schedule schedule = getSchedule(id);
        if (EmptyTool.notEmpty(dto.getTitle())) {
            schedule.updateTitle(dto.getTitle());
        }
        if (EmptyTool.notEmpty(dto.getContents())) {
            schedule.updateContents(dto.getContents());
        }
        return ScheduleResponseDto.fromSchedule(schedule);
    }

    public void delete(Long id) {
        Schedule schedule = getSchedule(id);
        scheduleRepository.delete(schedule);
    }

    private Schedule getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 ID 입니다. id: " + id));;
        if (!schedule.getMember().getId().equals(MemberContext.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 ID와 작성자 ID가 일치하지 않습니다.");
        }
        return schedule;
    }
}
