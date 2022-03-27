package com.showing.backend.db.repository.performance;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.api.response.ActorRes;
import com.showing.backend.db.entity.performance.QActor;
import com.showing.backend.db.entity.performance.QCasting;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //
public class CastingRepositoryCustomImpl implements CastingRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory; // check

    QActor qActor = QActor.actor;
    QCasting qCasting = QCasting.casting;

    @Override
    public Optional<List<ActorRes>> getCastingBySeason(Long seasonPlaydbId) {
        List<ActorRes> res = jpaQueryFactory.select(Projections.constructor(ActorRes.class,qActor.id, qCasting.role, qActor.actorImage, qActor.actorName))
                .from(qCasting).join(qActor)
                .on(qActor.playdbId.eq(qCasting.actor.playdbId))
                .where(qCasting.season.playdbId.eq(seasonPlaydbId))
                .fetch();
        return Optional.ofNullable(res);
    }
}
