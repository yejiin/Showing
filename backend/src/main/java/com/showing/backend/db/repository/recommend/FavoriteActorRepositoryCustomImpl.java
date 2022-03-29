package com.showing.backend.db.repository.recommend;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.api.response.FavActorRes;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Actor;
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

    /**
     * performance table 에서 유저의 선호 배우리스트를 weight 내림차순으로 조회ㄴ한다.
     */
    @Override
    public Optional<List<FavActorRes>> getFavActorListByUser(User user) {
        List<FavActorRes> res = jpaQueryFactory.select(Projections.constructor(FavActorRes.class,qActor.actorName,qActor.actorImage))
                .from(qActor).join(qFavoriteActor)
                .on(qActor.eq(qFavoriteActor.actor))
                .where(qFavoriteActor.user.eq(user))
                .orderBy(qFavoriteActor.weight.desc())
                .fetch();

        return Optional.ofNullable(res);
    }

    /**
     * favorite_actor table 에서 유저의 선호 배우리스트를 weight 내림차순으로 count개 만큼 조회한다.
     */
    @Override
    public List<Actor> findTopCountByUserId(int count, Long userId) {
        return jpaQueryFactory.select(Projections.constructor(Actor.class, qActor.actor.id, qActor.actorName, qActor.playdbId, qActor.actorImage))
                              .from(qFavoriteActor)
                              .where(qFavoriteActor.user.id.eq(userId))
                              .orderBy(qFavoriteActor.weight.desc())
                              .limit(count)
                              .fetch();
    }
}
