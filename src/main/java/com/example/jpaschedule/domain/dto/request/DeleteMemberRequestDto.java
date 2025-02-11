package com.example.jpaschedule.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteMemberRequestDto {

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private final String password;

    public DeleteMemberRequestDto(String password) {
        this.password = password;
    }
}
