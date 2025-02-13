package com.example.jpaschedule.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule")
@SQLDelete(sql = "UPDATE schedule SET deleted_at = now() WHERE id = ?")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Schedule() {
    }

    private Schedule(String title, String contents, Member member) {
        this.title = title;
        this.contents = contents;
        this.member = member;
    }

    public static Schedule of(String title, String contents, Member member) {
        return new Schedule(title, contents, member);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
