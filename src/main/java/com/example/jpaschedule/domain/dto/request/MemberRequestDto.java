package com.example.jpaschedule.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberRequestDto {

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private final String password;

    private final String newPassword;

    private final String username;

    @Email(message = "이메일 형식으로 작성해 주세요.")
    private final String email;

    public MemberRequestDto(String password, String newPassword, String username, String email) {
        this.password = password;
        this.newPassword = newPassword;
        this.username = username;
        this.email = email;
    }
}
