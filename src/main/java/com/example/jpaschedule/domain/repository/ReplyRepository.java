package com.example.jpaschedule.domain.repository;

import com.example.jpaschedule.domain.dto.ReplyCountDto;
import com.example.jpaschedule.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllBySchedule_Id(Long scheduleId);

    @Query("SELECT new com.example.jpaschedule.domain.dto.ReplyCountDto(r.schedule.id, count(r)) " +
            "FROM Reply r " +
            "WHERE r.schedule.id in :scheduleIds " +
            "GROUP BY r.schedule.id")
    List<ReplyCountDto> countByScheduleIds(List<Long> scheduleIds);
}
