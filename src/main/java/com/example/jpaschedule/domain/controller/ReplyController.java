package com.example.jpaschedule.domain.controller;

import com.example.jpaschedule.domain.dto.request.ReplyRequestDto;
import com.example.jpaschedule.domain.dto.response.ReplyResponseDto;
import com.example.jpaschedule.domain.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules/{id}")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/replies")
    public ResponseEntity<ReplyResponseDto> create(@PathVariable("id") Long id, @RequestBody ReplyRequestDto dto) {
        return ResponseEntity.ok(replyService.create(id, dto.getContents()));
    }

    @GetMapping("/replies")
    public ResponseEntity<List<ReplyResponseDto>> findAll(@PathVariable("id") Long id) {
        return ResponseEntity.ok(replyService.findAll(id));
    }
}
