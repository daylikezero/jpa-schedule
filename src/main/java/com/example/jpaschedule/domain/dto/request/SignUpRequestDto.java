package com.example.jpaschedule.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "유저명은 필수값입니다.")
    private final String username;

    /*@Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[!@#$%^&*()-+=]).{8,}$",
            message = "비밀번호 조건에 맞지 않습니다.")*/
    @NotBlank(message = "비밀번호는 필수값입니다.")
    private final String password;

    @Email(message = "이메일 형식으로 작성해 주세요.")
    private final String email;

    public SignUpRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
