package com.example.jpaschedule.domain.dto.response;

import com.example.jpaschedule.common.util.LocalDateTimeUtils;
import com.example.jpaschedule.domain.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;
    private final String username;
    private final String email;
    private final String createdAt;
    private final String updatedAt;

    private MemberResponseDto(Long id, String username, String email, String createdAt, String updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static MemberResponseDto fromMember(Member member) {
        return new MemberResponseDto(member.getId(),
                member.getUsername(),
                member.getEmail(),
                LocalDateTimeUtils.formatToYmdHms(member.getCreatedAt()),
                LocalDateTimeUtils.formatToYmdHms(member.getUpdatedAt()));
    }
}
