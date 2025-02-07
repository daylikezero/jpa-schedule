package com.example.jpaschedule.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final Long memberId;

    private final String title;

    private final String contents;

    public CreateScheduleRequestDto(Long memberId, String title, String contents) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
    }
}
