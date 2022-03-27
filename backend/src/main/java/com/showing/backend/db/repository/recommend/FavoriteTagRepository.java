package com.showing.backend.db.repository.recommend;

import com.showing.backend.db.entity.recommend.FavoriteTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteTagRepository extends JpaRepository<FavoriteTag, Long> {
}
