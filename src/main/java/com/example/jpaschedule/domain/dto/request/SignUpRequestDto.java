package com.example.jpaschedule.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "유저명은 필수값입니다.")
    @Size(max = 10, message = "유저명은 10글자 이내로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[!@#$%^&*()-+=]).{8,}$",
            message = "비밀번호는 최소 8자 이상, 숫자, 영문 대소문자, 특수문자가 각각 1개 이상 포함되어야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수값입니다.")
    @Email(message = "이메일 형식으로 작성해 주세요.")
    private String email;

}
