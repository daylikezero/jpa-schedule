package com.example.jpaschedule.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private final String password;

    private final String newPassword;

    @Email(message = "이메일 형식으로 작성해 주세요.")
    private final String email;

    public UpdateMemberRequestDto(String password, String newPassword, String email) {
        this.password = password;
        this.newPassword = newPassword;
        this.email = email;
    }
}
