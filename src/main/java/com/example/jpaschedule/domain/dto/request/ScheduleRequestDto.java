package com.example.jpaschedule.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {

    private Long memberId;

    @NotBlank(message = "할일 제목은 필수값입니다.")
    @Size(max = 20, message = "할일 제목은 20글자 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "할일 내용은 필수값입니다.")
    private String contents;

    private String username;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

}
