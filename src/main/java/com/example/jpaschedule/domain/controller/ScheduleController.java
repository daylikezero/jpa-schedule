package com.example.jpaschedule.domain.controller;

import com.example.jpaschedule.domain.dto.request.ScheduleRequestDto;
import com.example.jpaschedule.domain.dto.response.ScheduleResponseDto;
import com.example.jpaschedule.domain.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(scheduleService.save(dto.getTitle(), dto.getContents()));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        return ResponseEntity.ok(scheduleService.update(id, dto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
