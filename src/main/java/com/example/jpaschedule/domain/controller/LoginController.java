package com.example.jpaschedule.domain.controller;

import com.example.jpaschedule.common.Const;
import com.example.jpaschedule.domain.dto.request.LoginRequestDto;
import com.example.jpaschedule.domain.dto.response.MemberResponseDto;
import com.example.jpaschedule.domain.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 2. 세션(Session)
    // 2-1. 세션 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequestDto dto, HttpServletRequest request) {
        MemberResponseDto responseDto = loginService.login(dto.getEmail(), dto.getPassword());

        HttpSession session = request.getSession();

        session.setAttribute(Const.SESSION_KEY, responseDto.getId());
        return ResponseEntity.ok().build();
    }

    // 2-2. 세션 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
