package com.showing.backend.db.repository.review;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.db.entity.QUser;
import com.showing.backend.db.entity.performance.QPerformance;
import com.showing.backend.db.entity.performance.QSeason;
import com.showing.backend.db.entity.review.QReview;
import com.showing.backend.db.entity.review.Review;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QReview qReview = QReview.review;
    QSeason qSeason = QSeason.season;
    QPerformance qPerformance = QPerformance.performance;

    /**
     * 해당 퍼포먼스에 대한 유저의 리뷰 목록을 가져온다.
     */
    @Override
    public List<Review> getReviewByPerformanceAndUser(Long performanceId, Long userId) {
        return jpaQueryFactory.select(qReview)
                .from(qReview)
                .join(qSeason).on(qReview.season.eq(qSeason))
                .join(qPerformance).on(qSeason.performance.eq(qPerformance))
                .where(qReview.user.id.eq(userId))
                .where(qPerformance.id.eq(performanceId))
                .orderBy(qReview.performanceDate.desc())
                .fetch();
    }
}
