package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.domain.dto.request.MemberRequestDto;
import com.example.jpaschedule.domain.dto.response.MemberResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.repository.MemberRepository;
import com.example.jpaschedule.config.context.MemberContext;
import com.example.jpaschedule.common.util.EmptyTool;
import com.example.jpaschedule.exception.CustomException;
import com.example.jpaschedule.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(String username, String password, String email) {
        Member savedMember = memberRepository.save(Member.of(username, password, email));
        return MemberResponseDto.fromMember(savedMember);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for (Member member : members) {
            memberResponseDtos.add(MemberResponseDto.fromMember(member));
        }
        return memberResponseDtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member findMember = findMember(id);
        return MemberResponseDto.fromMember(findMember);
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = findMember(id);
        // TODO 기존 비밀번호 검증 로직
        if (EmptyTool.notEmpty(dto.getUsername())) {
            member.updateUsername(dto.getUsername());
        }
        if (EmptyTool.notEmpty(dto.getNewPassword())) {
            member.updatePassword(dto.getNewPassword());
        }
        if (EmptyTool.notEmpty(dto.getEmail())) {
            member.updateEmail(dto.getEmail());
        }
        return MemberResponseDto.fromMember(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = findMember(id);
        memberRepository.delete(member);
    }

    public Member findMember(Long id) {
        if (!id.equals(MemberContext.getMemberId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, "요청 id를 확인해주세요. id = " + id);
        }
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        if (EmptyTool.notEmpty(member.getDeletedAt())) {
            throw new CustomException(ErrorCode.ENTITY_DELETED, String.valueOf(id));
        }
        return member;
    }
}
