package com.example.jpaschedule.domain.repository;

import com.example.jpaschedule.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllBySchedule_Id(Long scheduleId);
}
