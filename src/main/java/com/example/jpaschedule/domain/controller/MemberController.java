package com.example.jpaschedule.domain.controller;

import com.example.jpaschedule.domain.dto.request.DeleteMemberRequestDto;
import com.example.jpaschedule.domain.dto.request.SignUpRequestDto;
import com.example.jpaschedule.domain.dto.request.MemberRequestDto;
import com.example.jpaschedule.domain.dto.request.UpdatePasswordRequestDto;
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
        return ResponseEntity.ok(memberService.save(dto.getUsername(), dto.getPassword(), dto.getEmail()));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> update(@PathVariable Long id, @Valid @RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok(memberService.update(id, dto));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<MemberResponseDto> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordRequestDto dto) {
        memberService.updatePassword(id, dto.getOldPassword(), dto.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @Valid @RequestBody DeleteMemberRequestDto dto) {
        memberService.delete(id, dto);
        return ResponseEntity.ok().build();
    }
}
