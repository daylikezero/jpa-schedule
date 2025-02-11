package com.example.jpaschedule.domain.dto.response;

import com.example.jpaschedule.domain.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private MemberResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static MemberResponseDto fromMember(Member member) {
        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }
}
