package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.domain.dto.request.MemberRequestDto;
import com.example.jpaschedule.domain.dto.response.MemberResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.repository.MemberRepository;
import com.example.jpaschedule.filter.context.MemberContext;
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

    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for (Member member : members) {
            memberResponseDtos.add(new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail()));
        }
        return memberResponseDtos;
    }

    public MemberResponseDto findById(Long id) {
        Member findMember = getMember(id);
        return new MemberResponseDto(findMember.getId(), findMember.getUsername(), findMember.getEmail());
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = getMember(id, dto.getPassword());
        if (EmptyTool.notEmpty(dto.getUsername())) {
            member.updateUsername(dto.getUsername());
        }
        if (EmptyTool.notEmpty(dto.getNewPassword())) {
            member.updatePassword(dto.getNewPassword());
        }
        if (EmptyTool.notEmpty(dto.getEmail())) {
            member.updateEmail(dto.getEmail());
        }
        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }

    @Transactional
    public void delete(Long id, String password) {
        Member member = getMember(id, password);
        memberRepository.delete(member);
    }

    public Member getMember(Long id) {
        if (!id.equals(MemberContext.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "조회 권한이 없습니다.");
        }
        return memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    private Member getMember(Long id, String password) {
        Member member = getMember(id);
        if (!member.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return member;
    }
}
