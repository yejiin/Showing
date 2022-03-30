package com.showing.backend.api.service;

import com.showing.backend.api.response.FavTagRes;
import com.showing.backend.db.entity.Tag;
import com.showing.backend.db.entity.User;

import java.util.List;

public interface TagService {

    void setFavoriteTagWeight(User user, Tag tag, float weight);

    List<FavTagRes> getFavoriteTagList(User user);
}
