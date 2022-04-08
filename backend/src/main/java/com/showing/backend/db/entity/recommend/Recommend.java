package com.showing.backend.db.entity.recommend;

import com.showing.backend.db.entity.BaseTimeEntity;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Performance;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "recommend")
public class Recommend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommend_performance_id")
    private Performance recPerformance;
}
