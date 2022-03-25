package com.showing.backend.db.entity.review;

import com.showing.backend.db.entity.BaseTimeEntity;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Season;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @Column
    private LocalDateTime performanceDate;

    @Column
    private String reviewContent;

}
