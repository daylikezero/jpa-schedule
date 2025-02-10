package com.example.jpaschedule.service;

import com.example.jpaschedule.dto.response.MemberResponseDto;
import com.example.jpaschedule.entity.Member;
import com.example.jpaschedule.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public MemberResponseDto login(String email, String password) {
        Member member = memberRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }
}
