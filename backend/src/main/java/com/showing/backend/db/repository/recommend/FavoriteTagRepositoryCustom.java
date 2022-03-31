package com.showing.backend.db.repository.recommend;

import com.showing.backend.api.response.FavTagRes;
import com.showing.backend.db.entity.User;

import java.util.List;

public interface FavoriteTagRepositoryCustom {

    List<FavTagRes> getFavTagResListByUser(User user);

}
