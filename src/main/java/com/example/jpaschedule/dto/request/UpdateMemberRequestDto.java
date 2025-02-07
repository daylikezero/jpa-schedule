package com.example.jpaschedule.dto.request;

import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {

    private final String password;

    private final String newPassword;

    private final String email;

    public UpdateMemberRequestDto(String password, String newPassword, String email) {
        this.password = password;
        this.newPassword = newPassword;
        this.email = email;
    }
}
