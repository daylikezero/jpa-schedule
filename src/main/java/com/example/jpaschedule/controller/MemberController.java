package com.example.jpaschedule.controller;

import com.example.jpaschedule.dto.request.CreateMemberRequestDto;
import com.example.jpaschedule.dto.request.UpdateMemberRequestDto;
import com.example.jpaschedule.dto.response.MemberResponseDto;
import com.example.jpaschedule.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody CreateMemberRequestDto dto) {
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
    public ResponseEntity<MemberResponseDto> update(@PathVariable Long id, @RequestBody UpdateMemberRequestDto dto) {
        MemberResponseDto updateResponseDto = memberService.update(id, dto);
        return ResponseEntity.ok(updateResponseDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody UpdateMemberRequestDto dto) {
        memberService.delete(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
