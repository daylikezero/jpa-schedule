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
        Member savedMember = memberRepository.save(Member.of(username, password, email));
        return MemberResponseDto.fromMember(savedMember);
    }

    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for (Member member : members) {
            memberResponseDtos.add(MemberResponseDto.fromMember(member));
        }
        return memberResponseDtos;
    }

    public MemberResponseDto findById(Long id) {
        Member findMember = getMember(id);
        return MemberResponseDto.fromMember(findMember);
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = getMember(id);
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
        Member member = getMember(id);
        memberRepository.delete(member);
    }

    public Member getMember(Long id) {
        if (!id.equals(MemberContext.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "조회 권한이 없습니다.");
        }
        return memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }
}
