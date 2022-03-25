package com.showing.backend.db.entity.review;

import com.showing.backend.db.entity.performance.Casting;
import com.showing.backend.db.entity.performance.Season;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review_actor")
public class ReviewActor {

    public ReviewActor(Review review, Casting casting) {
        this.review = review;
        this.casting = casting;
    }

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
