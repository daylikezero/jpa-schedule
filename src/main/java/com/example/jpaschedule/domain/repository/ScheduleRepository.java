package com.example.jpaschedule.domain.repository;

import com.example.jpaschedule.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByMember_Id(Long memberId);
}
