package com.example.jpaschedule.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final Long memberId;

    @NotBlank(message = "할일 제목은 필수값입니다.")
    private final String title;

    @NotBlank(message = "할일 내용은 필수값입니다.")
    private final String contents;

    public CreateScheduleRequestDto(Long memberId, String title, String contents) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
    }
}
