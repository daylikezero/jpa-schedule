package com.example.jpaschedule.controller;

import com.example.jpaschedule.dto.request.CreateScheduleRequestDto;
import com.example.jpaschedule.dto.request.UpdateScheduleRequestDto;
import com.example.jpaschedule.dto.response.ScheduleResponseDto;
import com.example.jpaschedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto dto) {
        ScheduleResponseDto saveSchedule = scheduleService.save(dto.getMemberId(), dto.getTitle(), dto.getContents());
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
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto dto) {
        ScheduleResponseDto updateResponseDto = scheduleService.update(id, dto);
        return ResponseEntity.ok(updateResponseDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto dto) {
        scheduleService.delete(id, dto.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
