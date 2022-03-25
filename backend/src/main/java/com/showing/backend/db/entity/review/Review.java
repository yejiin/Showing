package com.showing.backend.db.entity.review;

import com.showing.backend.db.entity.CreatedTimeEntity;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Season;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "review")
public class Review extends CreatedTimeEntity {

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

    @Builder
    public Review(User user, Season season, LocalDateTime performanceDate, String reviewContent) {
        this.user = user;
        this.season = season;
        this.performanceDate = performanceDate;
        this.reviewContent = reviewContent;
    }

}
