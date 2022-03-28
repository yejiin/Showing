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

    @Override
    public Optional<Long> getRatingCountByUserAndType(User user, int type) {

        List<Long> count = jpaQueryFactory.select(qStarPoint.count())
                .from(qStarPoint).join(qPerformance)
                .on(qPerformance.eq(qStarPoint.performance))
                .where(qStarPoint.user.eq(user).and(qPerformance.performanceType.eq(type)))
                .fetch();

        return Optional.ofNullable(count.get(0));
    }

}
