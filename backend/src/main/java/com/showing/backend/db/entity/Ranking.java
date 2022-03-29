package com.showing.backend.db.entity;

import com.showing.backend.db.entity.performance.Performance;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ranking")
public class Ranking extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @Column
    private int ratingCount;

    @Column
    private Float averageRating;
}
