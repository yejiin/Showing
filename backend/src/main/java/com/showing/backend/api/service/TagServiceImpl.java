package com.showing.backend.api.service;

import com.showing.backend.db.entity.Tag;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.recommend.FavoriteTag;
import com.showing.backend.db.repository.recommend.FavoriteTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final FavoriteTagRepository favoriteTagRepository;

    /*
    선호 태그 가중치 변경
     */
    @Override
    public void setFavoriteTagWeight(User user, Tag tag, float weight) {

        FavoriteTag favoriteTag = favoriteTagRepository.findByUserAndTag(user,tag).orElse(null);
        // 기존에 선호태그에 없다면
        if(favoriteTag==null)
            // favoriteTag 추가해주기
            favoriteTag = FavoriteTag.builder().user(user)
                    .tag(tag).weight(0).build();

        favoriteTag.setWeight(favoriteTag.getWeight()+weight);
        if(favoriteTag.getWeight()<0) favoriteTag.setWeight(0);
        favoriteTagRepository.save(favoriteTag);
    }
}
