package com.example.jpaschedule.domain.repository;

import com.example.jpaschedule.common.util.EmptyTool;
import com.example.jpaschedule.domain.dto.request.ScheduleRequestDto;
import com.example.jpaschedule.domain.entity.QSchedule;
import com.example.jpaschedule.domain.entity.Schedule;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSchedule schedule = QSchedule.schedule;

    public Page<Schedule> findDynamicSchedules(ScheduleRequestDto dto, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(schedule.deletedAt.isNull());
        builder.and(likeTitle(dto.getTitle()));
        builder.and(likeContents(dto.getContents()));
        builder.and(likeUsername(dto.getUsername()));
        builder.and(eqMemberId(dto.getMemberId()));
        builder.and(betweenUpdatedAt(dto.getUpdatedAt()));

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

    private BooleanExpression likeTitle(String title) {
        return EmptyTool.empty(title) ? null : schedule.title.like("%" + title + "%");
    }

    private BooleanExpression likeContents(String contents) {
        return EmptyTool.empty(contents) ? null : schedule.contents.like("%" + contents + "%");
    }

    private BooleanExpression likeUsername(String username) {
        return EmptyTool.empty(username) ? null : schedule.member.username.like("%" + username + "%");
    }

    private BooleanExpression eqMemberId(Long memberId) {
        return EmptyTool.empty(memberId) ? null : schedule.member.id.eq(memberId);
    }

    private BooleanExpression betweenUpdatedAt(LocalDate updatedAt) {
        return EmptyTool.empty(updatedAt) ? null : schedule.updatedAt.between(updatedAt.atStartOfDay(), updatedAt.atStartOfDay().plusDays(1));
    }

}
