package com.showing.backend.db.entity.review;

import com.showing.backend.db.entity.BaseTimeEntity;
import com.showing.backend.db.entity.performance.Casting;
import com.showing.backend.db.entity.performance.Season;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "review_actor")
public class ReviewActor extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "casting_id")
    private Casting casting;
}
