package com.showing.backend.db.repository.recommend;

import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Actor;
import com.showing.backend.db.entity.recommend.FavoriteActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteActorRepository extends JpaRepository<FavoriteActor, Long>, FavoriteActorRepositoryCustom {

    FavoriteActor findByUserAndActor(User user, Actor actor);

}
