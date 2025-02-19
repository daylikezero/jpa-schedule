package com.example.jpaschedule.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "이메일은 필수값입니다.")
    @Email(message = "이메일 형식으로 작성해 주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;

}
