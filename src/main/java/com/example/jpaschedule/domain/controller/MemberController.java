package com.example.jpaschedule.domain.controller;

import com.example.jpaschedule.domain.dto.request.SignUpRequestDto;
import com.example.jpaschedule.domain.dto.request.MemberRequestDto;
import com.example.jpaschedule.domain.dto.response.MemberResponseDto;
import com.example.jpaschedule.domain.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@Valid @RequestBody SignUpRequestDto dto) {
        MemberResponseDto saveResponseDto = memberService.save(dto.getUsername(), dto.getPassword(), dto.getEmail());
        return ResponseEntity.ok(saveResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto findResponseDto = memberService.findById(id);
        return ResponseEntity.ok(findResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> update(@PathVariable Long id, @Valid @RequestBody MemberRequestDto dto) {
        MemberResponseDto updateResponseDto = memberService.update(id, dto);
        return ResponseEntity.ok(updateResponseDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.ok().build();
    }
}
