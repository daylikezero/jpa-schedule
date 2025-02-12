package com.example.jpaschedule.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReplyRequestDto {

    private final Long scheduleId;
    @NotBlank(message = "댓글 내용은 필수값입니다.")
    private final String contents;

    public ReplyRequestDto(Long scheduleId, String contents) {
        this.scheduleId = scheduleId;
        this.contents = contents;
    }
}
