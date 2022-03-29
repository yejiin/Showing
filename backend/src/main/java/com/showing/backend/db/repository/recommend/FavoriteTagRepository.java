package com.showing.backend.db.repository.recommend;

import com.showing.backend.db.entity.Tag;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.recommend.FavoriteTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteTagRepository extends JpaRepository<FavoriteTag, Long> {

    /**
     * favorite_tag 테이블에서 유저별 선호 태그를 조회한다.
     */
    List<FavoriteTag> findByUser(User user);

    /**
     * favorite_tag 테이블에서 유저별 해당 선호 태그를 조회한다.
     */
    Optional<FavoriteTag> findByUserAndTag(User user, Tag tag);
}
