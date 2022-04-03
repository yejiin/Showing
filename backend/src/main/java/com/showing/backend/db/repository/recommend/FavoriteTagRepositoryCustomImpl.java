package com.showing.backend.db.repository.recommend;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.showing.backend.api.response.FavActorRes;
import com.showing.backend.api.response.FavTagRes;
import com.showing.backend.db.entity.QTag;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.recommend.QFavoriteTag;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FavoriteTagRepositoryCustomImpl implements FavoriteTagRepositoryCustom  {

    private final JPAQueryFactory jpaQueryFactory;

    QTag qTag = QTag.tag;
    QFavoriteTag qFavoriteTag = QFavoriteTag.favoriteTag;

    /**
     * favorite_tag 테이블에서 유저의 선호 태그를 조회한다.
     */
    public List<FavTagRes> getFavTagResListByUser(User user) {

        return jpaQueryFactory.select(Projections.constructor(FavTagRes.class,qTag.tagName,qFavoriteTag.weight.multiply(100).intValue()))
                .from(qFavoriteTag)
                .join(qTag)
                .on(qFavoriteTag.tag.eq(qTag))
                .where(qFavoriteTag.user.eq(user))
                .orderBy(qFavoriteTag.weight.desc())
                .limit(10)
                .fetch();
    }
}
