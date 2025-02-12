package com.example.jpaschedule.domain.repository;

import com.example.jpaschedule.domain.entity.QSchedule;
import com.example.jpaschedule.domain.entity.Schedule;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<Schedule> findDynamicSchedules(BooleanBuilder builder, Pageable pageable) {
        QSchedule schedule = QSchedule.schedule;
        List<Schedule> schedules = jpaQueryFactory.selectFrom(schedule)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(schedule.updatedAt.desc())
                .fetch();

        Long count = jpaQueryFactory.select(schedule.count())
                .from(schedule)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(schedules, pageable, count);
    }
}
