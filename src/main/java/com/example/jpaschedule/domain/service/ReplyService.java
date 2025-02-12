package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.config.context.MemberContext;
import com.example.jpaschedule.domain.dto.response.ReplyResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.entity.Reply;
import com.example.jpaschedule.domain.entity.Schedule;
import com.example.jpaschedule.domain.repository.ReplyRepository;
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
    public ReplyResponseDto create(Long id, String contents) {
        Schedule schedule = scheduleService.findSchedule(id);
        Member member = memberService.findMember(MemberContext.getMemberId());
        Reply reply = replyRepository.save(Reply.of(contents, member, schedule));
        return ReplyResponseDto.fromReply(reply);
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAll(Long id) {
        List<Reply> replies = replyRepository.findAllBySchedule_Id(id);
        List<ReplyResponseDto> replyResponseDtoList = new ArrayList<>();
        for (Reply reply : replies) {
            replyResponseDtoList.add(ReplyResponseDto.fromReply(reply));
        }
        return replyResponseDtoList;
    }
}
