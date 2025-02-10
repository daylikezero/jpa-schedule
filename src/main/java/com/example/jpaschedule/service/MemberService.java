package com.example.jpaschedule.service;

import com.example.jpaschedule.dto.request.UpdateMemberRequestDto;
import com.example.jpaschedule.dto.response.MemberResponseDto;
import com.example.jpaschedule.entity.Member;
import com.example.jpaschedule.repository.MemberRepository;
import com.example.jpaschedule.service.context.MemberContext;
import com.example.jpaschedule.util.EmptyTool;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto save(String username, String password, String email) {
        Member savedMember = memberRepository.save(new Member(username, password, email));
        return new MemberResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());
    }

    // TODO 유저 전체 조회 권한 필요 (관리자)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for (Member member : members) {
            memberResponseDtos.add(new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail()));
        }
        return memberResponseDtos;
    }

    public MemberResponseDto findById(Long id) {
        if (!id.equals(MemberContext.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "조회 권한이 없습니다.");
        }
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        return new MemberResponseDto(findMember.getId(), findMember.getUsername(), findMember.getEmail());
    }

    @Transactional
    public MemberResponseDto update(Long id, UpdateMemberRequestDto dto) {
        Member member = getMember(id, dto.getPassword());
        member.updateEmail(dto.getEmail());
        if (EmptyTool.notEmpty(dto.getNewPassword())) {
            member.updatePassword(dto.getNewPassword());
        }
        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }

    @Transactional
    public void delete(Long id, String password) {
        Member member = getMember(id, password);
        memberRepository.delete(member);
    }

    private Member getMember(Long id, String password) {
        if (!id.equals(MemberContext.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "조회 권한이 없습니다.");
        }
        Member member = memberRepository.findByIdOrElseThrow(id);
        if (!member.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return member;
    }
}
