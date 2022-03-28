package com.showing.backend.db.repository.performance;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.QPerformance;
import com.showing.backend.db.entity.performance.QStarPoint;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StarPointRepositoryCustomImpl implements StarPointRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory; // check

    QStarPoint qStarPoint = QStarPoint.starPoint;
    QPerformance qPerformance = QPerformance.performance;

    /**
     * star_point table 에서 performance_type 별로 유저가 준 별점 갯수를 조회한다.
     */
    @Override
    public Long getRatingCountByUserAndType(User user, int type) {

        List<Long> count = jpaQueryFactory.select(qStarPoint.count())
                .from(qStarPoint).join(qPerformance)
                .on(qPerformance.eq(qStarPoint.performance))
                .where(qStarPoint.user.eq(user).and(qPerformance.performanceType.eq(type)))
                .fetch();

        return Optional.ofNullable(count.get(0)).orElse(0L);
    }

    /**
     * star_point table 에서 rating 별로 유저가 준 별점 갯수를 조회한다.
     */
    @Override
    public Long getRatingCountByUserAndRating(User user,int rating) {

        return Optional.ofNullable(jpaQueryFactory.select(qStarPoint.count())
                .from(qStarPoint)
                .where(qStarPoint.user.eq(user).and(qStarPoint.rating.eq(rating)))
                .fetch().get(0)).orElse(0L);

    }

    /**
     * star_point table 에서 유저가 준 별점의 평균을 조회한다.
     */
    @Override
    public Double getRatingAvgByUser(User user) {

        return Optional.ofNullable(jpaQueryFactory.select(qStarPoint.rating.avg())
                .from(qStarPoint)
                .where(qStarPoint.user.eq(user))
                .fetch().get(0)).orElse(0D);
    }


}
