package com.example.jpaschedule.domain.dto.response;

import com.example.jpaschedule.domain.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String contents;
    private final Integer replyCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;


    private ScheduleResponseDto(Long id, String username, String title, String contents, Integer replyCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }

}
