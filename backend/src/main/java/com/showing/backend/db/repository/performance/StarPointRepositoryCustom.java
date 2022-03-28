package com.showing.backend.db.repository.performance;


import com.showing.backend.db.entity.User;

public interface StarPointRepositoryCustom {

    Long getRatingCountByUserAndType(User user, int type);

    Long getRatingCountByUserAndRating(User user,int rating);

    Double getRatingAvgByUser(User user);
}
