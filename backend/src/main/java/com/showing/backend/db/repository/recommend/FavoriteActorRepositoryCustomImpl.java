package com.showing.backend.db.repository.recommend;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.api.response.FavActorRes;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.QActor;
import com.showing.backend.db.entity.recommend.QFavoriteActor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FavoriteActorRepositoryCustomImpl implements FavoriteActorRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QActor qActor = QActor.actor;
    QFavoriteActor qFavoriteActor = QFavoriteActor.favoriteActor;

    @Override
    public Optional<List<FavActorRes>> getFavActorListByUser(User user) {
        List<FavActorRes> res = jpaQueryFactory.select(Projections.constructor(FavActorRes.class,qActor.actorName,qActor.actorName))
                .from(qActor).join(qFavoriteActor)
                .on(qActor.eq(qFavoriteActor.actor))
                .where(qFavoriteActor.user.eq(user))
                .orderBy(qFavoriteActor.weight.desc())
                .fetch();

        return Optional.ofNullable(res);
    }
}
