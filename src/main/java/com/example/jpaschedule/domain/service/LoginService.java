package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.domain.dto.response.MemberResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.domain.repository.LoginRepository;
import com.example.jpaschedule.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public MemberResponseDto login(String email, String password) {
        Member member = loginRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }
}
