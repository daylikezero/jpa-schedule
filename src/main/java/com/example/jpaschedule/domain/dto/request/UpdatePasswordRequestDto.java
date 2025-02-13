package com.example.jpaschedule.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {

    @NotBlank(message = "기존 비밀번호는 필수값입니다.")
    private String oldPassword;

    @NotBlank(message = "신규 비밀번호는 필수값입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[!@#$%^&*()-+=]).{8,}$",
            message = "비밀번호는 최소 8자 이상, 숫자, 영문 대소문자, 특수문자가 각각 1개 이상 포함되어야 합니다.")
    private String newPassword;

}
