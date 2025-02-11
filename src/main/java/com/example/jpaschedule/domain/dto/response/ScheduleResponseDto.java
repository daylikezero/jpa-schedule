package com.example.jpaschedule.domain.dto.response;

import com.example.jpaschedule.domain.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String contents;

    private ScheduleResponseDto(Long id, String username, String title, String contents) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    public static ScheduleResponseDto fromSchedule(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getMember().getUsername(), schedule.getTitle(), schedule.getContents());
    }
}
