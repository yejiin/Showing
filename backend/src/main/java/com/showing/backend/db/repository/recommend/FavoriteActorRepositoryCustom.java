package com.showing.backend.db.repository.recommend;

import com.showing.backend.api.response.FavActorRes;
import com.showing.backend.db.entity.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteActorRepositoryCustom {

    /**
     * favorite_actor와 actor 테이블에서 해당 유저의 favorite actor의 정보를 조회한다.
     */
    Optional<List<FavActorRes>> getFavActorListByUser(User user);
}
