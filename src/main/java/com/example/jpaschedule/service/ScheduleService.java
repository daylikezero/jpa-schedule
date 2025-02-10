package com.example.jpaschedule.service;

import com.example.jpaschedule.dto.request.UpdateScheduleRequestDto;
import com.example.jpaschedule.dto.response.ScheduleResponseDto;
import com.example.jpaschedule.entity.Member;
import com.example.jpaschedule.entity.Schedule;
import com.example.jpaschedule.repository.MemberRepository;
import com.example.jpaschedule.repository.ScheduleRepository;
import com.example.jpaschedule.filter.context.MemberContext;
import com.example.jpaschedule.util.EmptyTool;
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

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ScheduleResponseDto save(Long memberId, String title, String contents) {
        Member findMember = memberRepository.findByIdOrElseThrow(memberId);
        Schedule schedule = new Schedule(title, contents);
        schedule.setMember(findMember);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule.getId(), schedule.getMember().getUsername(), schedule.getTitle(), schedule.getContents());
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAllByMember_Id(MemberContext.getMemberId());
        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleResponseDtos.add(new ScheduleResponseDto(schedule.getId(), schedule.getMember().getUsername(), schedule.getTitle(), schedule.getContents()));
        }
        return scheduleResponseDtos;
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getMember().getUsername(), findSchedule.getTitle(), findSchedule.getContents());
    }

    @Transactional
    public ScheduleResponseDto update(Long id, UpdateScheduleRequestDto dto) {
        Schedule schedule = getSchedule(id, dto.getMemberId());
        if (EmptyTool.notEmpty(dto.getTitle())) {
            schedule.updateTitle(dto.getTitle());
        }
        if (EmptyTool.notEmpty(dto.getContents())) {
            schedule.updateContents(dto.getContents());
        }
        return new ScheduleResponseDto(schedule.getId(), schedule.getMember().getUsername(), schedule.getTitle(), schedule.getContents());
    }

    private Schedule getSchedule(Long id, Long memberId) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        if (!schedule.getMember().getId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "memberId is not match");
        }
        return schedule;
    }

    public void delete(Long id, Long memberId) {
        Schedule schedule = getSchedule(id, memberId);
        scheduleRepository.delete(schedule);
    }
}
