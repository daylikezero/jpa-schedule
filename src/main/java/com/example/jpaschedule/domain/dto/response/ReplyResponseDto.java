package com.example.jpaschedule.domain.dto.response;

import com.example.jpaschedule.domain.entity.Reply;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {

    private final Long id;
    private final Long scheduleId;
    private final Long memberId;
    private final String username;
    private final String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    private ReplyResponseDto(Long id, Long scheduleId, Long memberId, String username, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.username = username;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ReplyResponseDto fromReply(Reply reply) {
        return new ReplyResponseDto(reply.getId(),
                reply.getSchedule().getId(),
                reply.getMember().getId(),
                reply.getMember().getUsername(),
                reply.getContents(),
                reply.getCreatedAt(),
                reply.getUpdatedAt());
    }
}
