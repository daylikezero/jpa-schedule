package com.example.jpaschedule.dto.request;

import lombok.Getter;

@Getter
public class CreateMemberRequestDto {

    private final String username;

    private final String password;

    private final String email;

    public CreateMemberRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
