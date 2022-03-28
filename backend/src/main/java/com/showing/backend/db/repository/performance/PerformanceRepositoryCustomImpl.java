package com.showing.backend.db.repository.performance;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.api.response.PerformanceRes;
import com.showing.backend.db.entity.QRanking;
import com.showing.backend.db.entity.performance.QPerformance;
import com.showing.backend.db.entity.performance.QSeason;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PerformanceRepositoryCustomImpl implements PerformanceRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    QPerformance qPerformance = QPerformance.performance;
    QRanking qRanking = QRanking.ranking1;
    QSeason qSeason = QSeason.season;

    /*
     * 공연 타입 별 평균 별점 순으로 공연 목록 조회
     */
    @Override
    public List<PerformanceRes> findByTypeAndStarPointAvg(int performanceType) {
        return jpaQueryFactory.select(Projections.constructor(PerformanceRes.class,
                        qPerformance.id, qPerformance.performanceName, qPerformance.performanceType, qPerformance.lastSeasonId,
                        qPerformance.performanceImage, qSeason.startDate, qSeason.endDate, qSeason.proceedFlag, qRanking.averageRating))
                .from(qPerformance).join(qRanking)
                .on(qPerformance.id.eq(qRanking.performance.id))
                .join(qSeason)
                .on(qPerformance.lastSeasonId.eq(qSeason.id))
                .where(qPerformance.performanceType.eq(performanceType))
                .orderBy(qRanking.averageRating.desc()).limit(15)
                .fetch();
    }
}
