package com.example.jpaschedule.dto.request;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private final Long memberId;

    private final String title;

    private final String contents;

    public UpdateScheduleRequestDto(Long memberId, String title, String contents) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
    }
}
