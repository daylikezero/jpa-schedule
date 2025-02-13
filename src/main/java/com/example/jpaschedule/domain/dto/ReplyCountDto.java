package com.example.jpaschedule.domain.dto;

import lombok.Getter;

@Getter
public class ReplyCountDto {
    private final Long scheduleId;
    private final Long count;

    public ReplyCountDto(Long scheduleId, Long count) {
        this.scheduleId = scheduleId;
        this.count = count;
    }
}
