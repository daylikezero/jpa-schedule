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
        ScheduleResponseDto saveSchedule = scheduleService.save(dto.getTitle(), dto.getContents());
        return ResponseEntity.ok(saveSchedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto findResponseDto = scheduleService.findById(id);
        return ResponseEntity.ok(findResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto updateResponseDto = scheduleService.update(id, dto);
        return ResponseEntity.ok(updateResponseDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
