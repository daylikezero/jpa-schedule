package com.example.jpaschedule.domain.repository;

import com.example.jpaschedule.domain.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s " +
            "FROM Schedule s " +
            "LEFT JOIN s.member m " +
            "LEFT JOIN s.replies r " +
            "WHERE s.deletedAt IS NULL ")
    Page<Schedule> findAllByOrderByUpdatedAtDesc(Pageable pageable);
}
