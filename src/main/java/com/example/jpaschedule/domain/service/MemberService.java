package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.common.util.EmptyTool;
import com.example.jpaschedule.config.PasswordEncoder;
import com.example.jpaschedule.config.context.MemberContext;
import com.example.jpaschedule.domain.dto.request.DeleteMemberRequestDto;
import com.example.jpaschedule.domain.dto.request.MemberRequestDto;
import com.example.jpaschedule.domain.dto.response.MemberResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.repository.MemberRepository;
import com.example.jpaschedule.exception.CustomException;
import com.example.jpaschedule.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponseDto signup(String username, String password, String email) {
        if (findByEmail(email).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
        String encodePassword = passwordEncoder.encode(password);
        Member savedMember = memberRepository.save(Member.of(username, encodePassword, email));
        return MemberResponseDto.fromMember(savedMember);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        for (Member member : members) {
            memberResponseDtoList.add(MemberResponseDto.fromMember(member));
        }
        return memberResponseDtoList;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member findMember = findMember(id);
        return MemberResponseDto.fromMember(findMember);
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = findMember(id);
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_INCORRECT);
        }
        if (EmptyTool.notEmpty(dto.getUsername())) {
            member.updateUsername(dto.getUsername());
        }
        if (EmptyTool.notEmpty(dto.getEmail())) {
            if (findByEmail(dto.getEmail()).isPresent()) {
                throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
            }
            member.updateEmail(dto.getEmail());
        }
        return MemberResponseDto.fromMember(member);
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        Member member = findMember(id);
        if (!passwordEncoder.matches(oldPassword, member.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_INCORRECT);
        }
        member.updatePassword(passwordEncoder.encode(newPassword));
    }

    @Transactional
    public void delete(Long id, @Valid DeleteMemberRequestDto dto) {
        Member member = findMember(id);
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_INCORRECT);
        }
        memberRepository.delete(member);
    }

    public Member findMember(Long id) {
        if (!id.equals(MemberContext.getMemberId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED, String.valueOf(id));
        }
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        if (EmptyTool.notEmpty(member.getDeletedAt())) {
            throw new CustomException(ErrorCode.ENTITY_DELETED, String.valueOf(id));
        }
        return member;
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
