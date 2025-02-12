package com.example.jpaschedule.domain.dto.response;

import com.example.jpaschedule.domain.entity.Reply;
import lombok.Getter;

@Getter
public class ReplyResponseDto {

    private final Long id;

    private final Long scheduleId;

    private final Long memberId;

    private final String username;

    private final String contents;

    private ReplyResponseDto(Long id, Long scheduleId, Long memberId, String username, String contents) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.username = username;
        this.contents = contents;
    }

    public static ReplyResponseDto fromReply(Reply reply) {
        return new ReplyResponseDto(reply.getId(), reply.getSchedule().getId(), reply.getMember().getId(), reply.getMember().getUsername(), reply.getContents());
    }
}
