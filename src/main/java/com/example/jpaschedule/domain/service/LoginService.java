package com.example.jpaschedule.domain.service;

import com.example.jpaschedule.config.PasswordEncoder;
import com.example.jpaschedule.domain.dto.response.MemberResponseDto;
import com.example.jpaschedule.domain.entity.Member;
import com.example.jpaschedule.exception.CustomException;
import com.example.jpaschedule.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto login(String email, String password) {
        Member member = memberService.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.EMAIL_INCORRECT));
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_INCORRECT);
        }
        return MemberResponseDto.fromMember(member);
    }
}
