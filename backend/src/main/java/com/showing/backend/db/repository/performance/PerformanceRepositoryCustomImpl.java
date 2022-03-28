package com.showing.backend.db.repository.performance;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.db.entity.performance.QPerformance;
import com.showing.backend.db.entity.performance.QStarPoint;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PerformanceRepositoryCustomImpl implements PerformanceRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QPerformance qPerformance = QPerformance.performance;
    QStarPoint qStarPoint = QStarPoint.starPoint;

    /**
     * 사용자가 별점10점 중 starPoint점 이상 등록한 공연 Id 리스트를 조회한다.
     */
    @Override
    public List<Long> findByUserIdAndStarPointGreaterThanEqual(Long userId, int starPoint) {
        return jpaQueryFactory.select(qPerformance.id)
                              .from(qPerformance)
                              .join(qStarPoint).on(qStarPoint.performance.id.eq(qPerformance.id))
                              .where(qStarPoint.user.id.eq(userId))
                              .where(qStarPoint.rating.goe(starPoint))
                              .fetch();
    }
}
