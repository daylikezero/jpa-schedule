package com.example.jpaschedule.domain.controller;

import com.example.jpaschedule.domain.dto.request.ReplyRequestDto;
import com.example.jpaschedule.domain.dto.response.ReplyResponseDto;
import com.example.jpaschedule.domain.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponseDto> create(@RequestBody ReplyRequestDto dto) {
        return ResponseEntity.ok(replyService.create(dto.getScheduleId(), dto.getContents()));
    }

    @GetMapping
    public ResponseEntity<List<ReplyResponseDto>> findAll(@RequestBody ReplyRequestDto dto) {
        return ResponseEntity.ok(replyService.findAll(dto.getScheduleId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponseDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(replyService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReplyResponseDto> update(@PathVariable("id") Long id, @RequestBody ReplyRequestDto dto) {
        return ResponseEntity.ok(replyService.update(id, dto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        replyService.delete(id);
        return ResponseEntity.ok().build();
    }
}
