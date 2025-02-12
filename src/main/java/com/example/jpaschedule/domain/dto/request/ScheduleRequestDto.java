package com.example.jpaschedule.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    private final Long memberId;

    @NotBlank(message = "할일 제목은 필수값입니다.")
    @Size(max = 20, message = "할일 제목은 20글자 이내로 입력해주세요.")
    private final String title;

    @NotBlank(message = "할일 내용은 필수값입니다.")
    private final String contents;

    private final String username;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate updatedAt;

}
