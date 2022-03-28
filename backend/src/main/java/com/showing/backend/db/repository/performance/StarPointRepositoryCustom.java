package com.showing.backend.db.repository.performance;


import com.showing.backend.db.entity.User;

import java.util.List;
import java.util.Optional;

public interface StarPointRepositoryCustom {

    Optional<Long> getRatingCountByUserAndType(User user, int type);

    Long getRatingCountByUserAndRating(User user,int rating);
}
