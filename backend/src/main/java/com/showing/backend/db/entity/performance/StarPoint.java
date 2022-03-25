package com.showing.backend.db.entity.performance;

import com.showing.backend.db.entity.BaseTimeEntity;
import com.showing.backend.db.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "star_point")
public class StarPoint extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @Column
    private int rating;

    @Builder
    public StarPoint(Long id, User user, Performance performance, int rating) {
        this.id = id;
        this.user = user;
        this.performance = performance;
        this.rating = rating;
    }
}
