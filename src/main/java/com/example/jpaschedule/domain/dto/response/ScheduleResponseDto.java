package com.example.jpaschedule.domain.dto.response;

import com.example.jpaschedule.common.util.LocalDateTimeUtils;
import com.example.jpaschedule.domain.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String contents;
    private final Integer replyCount;
    private final String createdAt;
    private final String updatedAt;


    private ScheduleResponseDto(Long id, String username, String title, String contents, Integer replyCount, String createdAt, String updatedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.replyCount = replyCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ScheduleResponseDto fromSchedule(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(),
                schedule.getMember().getUsername(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getReplies().size(),
                LocalDateTimeUtils.formatToYmdHms(schedule.getCreatedAt()),
                LocalDateTimeUtils.formatToYmdHms(schedule.getUpdatedAt()));
    }

}
