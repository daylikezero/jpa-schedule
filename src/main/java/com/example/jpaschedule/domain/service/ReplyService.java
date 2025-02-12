package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.common.util.EmptyTool;
import com.example.jpaschedule.config.context.MemberContext;
import com.example.jpaschedule.domain.dto.request.ReplyRequestDto;
import com.example.jpaschedule.domain.dto.response.ReplyResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.entity.Reply;
import com.example.jpaschedule.domain.entity.Schedule;
import com.example.jpaschedule.domain.repository.ReplyRepository;
import com.example.jpaschedule.exception.CustomException;
import com.example.jpaschedule.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ScheduleService scheduleService;
    private final MemberService memberService;

    @Transactional
    public ReplyResponseDto create(Long scheduleId, String contents) {
        Schedule schedule = scheduleService.findSchedule(scheduleId);
        Member member = memberService.findMember(MemberContext.getMemberId());
        Reply reply = replyRepository.save(Reply.of(contents, member, schedule));
        return ReplyResponseDto.fromReply(reply);
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAll(Long scheduleId) {
        List<Reply> replies = replyRepository.findAllBySchedule_Id(scheduleId);
        List<ReplyResponseDto> replyResponseDtoList = new ArrayList<>();
        for (Reply reply : replies) {
            if (EmptyTool.empty(reply.getDeletedAt())) {
                replyResponseDtoList.add(ReplyResponseDto.fromReply(reply));
            }
        }
        return replyResponseDtoList;
    }

    @Transactional(readOnly = true)
    public ReplyResponseDto findById(Long id) {
        return ReplyResponseDto.fromReply(findReply(id));
    }

    @Transactional
    public ReplyResponseDto update(Long id, ReplyRequestDto dto) {
        Reply reply = findReply(id);
        validReplyMemberId(reply);
        reply.updateContents(dto.getContents());
        return ReplyResponseDto.fromReply(reply);
    }

    @Transactional
    public void delete(Long id) {
        Reply reply = findReply(id);
        validReplyMemberId(reply);
        replyRepository.delete(reply);
    }

    private Reply findReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.REPLY_NOT_FOUND));
        if (EmptyTool.notEmpty(reply.getDeletedAt())) {
            throw new CustomException(ErrorCode.ENTITY_DELETED, String.valueOf(id));
        }
        return reply;
    }

    private void validReplyMemberId(Reply reply) {
        if (!reply.getMember().getId().equals(MemberContext.getMemberId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, "로그인 ID와 작성자 ID가 일치하지 않습니다.");
        }
    }
}
