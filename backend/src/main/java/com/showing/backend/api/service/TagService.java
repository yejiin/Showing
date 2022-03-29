package com.showing.backend.api.service;

import com.showing.backend.db.entity.Tag;
import com.showing.backend.db.entity.User;

public interface TagService {

    void setFavoriteTagWeight(User user, Tag tag, float weight);

}
