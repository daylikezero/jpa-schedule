package com.example.jpaschedule.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Table(name = "reply")
@SQLDelete(sql = "UPDATE reply SET deleted_at = now() WHERE id = ?")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Reply() {
    }

    private Reply(String contents, Member member, Schedule schedule) {
        this.contents = contents;
        this.member = member;
        this.schedule = schedule;
    }

    public static Reply of(String contents, Member member, Schedule schedule) {
        return new Reply(contents, member, schedule);
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
