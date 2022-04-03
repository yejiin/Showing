package com.showing.backend.db.repository.performance;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.api.response.PerformanceRes;
import com.showing.backend.db.entity.QRanking;
import com.showing.backend.db.entity.performance.QPerformance;
import com.showing.backend.db.entity.performance.QSeason;
import com.showing.backend.db.entity.performance.QStarPoint;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PerformanceRepositoryCustomImpl implements PerformanceRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QPerformance qPerformance = QPerformance.performance;
    QStarPoint qStarPoint = QStarPoint.starPoint;
    QRanking qRanking = QRanking.ranking;
    QSeason qSeason = QSeason.season;

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

    /**
     * 진행 중인 공연 중 랜덤으로 count개 조회한다.
     *
     * @return
     */
    @Override
    public List<PerformanceRes> findByProceedFlagIs1(int count) {
        return jpaQueryFactory.select(Projections.constructor(PerformanceRes.class,
                        qPerformance.id.as("performanceId"),
                        qPerformance.performanceName.as("performanceName"),
                        qPerformance.performanceType.as("performanceType"),
                        qPerformance.lastSeasonId.as("lastSeasonId"),
                        qSeason.seasonImage.as("lastSeasonImage"),
                        qSeason.startDate.as("lastSeasonStartDate"),
                        qSeason.endDate.as("lastSeasonEndDate"),
                        qSeason.proceedFlag.as("lastSeasonProceedFlag"),
                        ExpressionUtils.as(
                                JPAExpressions.select(qStarPoint.rating.avg())
                                        .from(qStarPoint)
                                        .where(qStarPoint.performance.id.eq(qPerformance.id))
                                , "starPointAverage"
                        )
                )).from(qPerformance)
                .join(qSeason).on(qSeason.id.eq(qPerformance.lastSeasonId))
                .where(qSeason.proceedFlag.eq(1))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }
}
