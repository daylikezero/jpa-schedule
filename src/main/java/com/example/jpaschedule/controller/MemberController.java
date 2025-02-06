package com.example.jpaschedule.controller;

import com.example.jpaschedule.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

}
