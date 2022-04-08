package com.showing.backend.api.service;

import com.showing.backend.api.response.ActorRes;
import com.showing.backend.api.response.FavActorRes;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Actor;
import com.showing.backend.db.entity.recommend.FavoriteActor;
import com.showing.backend.db.repository.performance.CastingRepository;
import com.showing.backend.db.repository.recommend.FavoriteActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ActorServiceImpl implements ActorService {

    private final CastingRepository castingRepository;
    private final FavoriteActorRepository favoriteActorRepository;

    @Override
    public List<ActorRes> getSeasonCastingList(Long seasonPlaydbId) {

        // seasonPlaydbId에 해당하는 캐스팅 배우 목록
        return castingRepository.getCastingBySeason(seasonPlaydbId).orElseThrow(() -> new NotFoundException(ErrorCode.CASTING_NOT_FOUND));
    }

    /**
     * user의 선호 배우 가중치를 삽입, 수정한다.
     * weight는 1 또는 -1로 리뷰를 추가할 때는 1 증가, 리뷰를 삭제할 때는 1 감소로 처리한다.
     */
    @Override
    public void setFavoriteActorWeight(int weight, User user, Actor actor) {
        FavoriteActor favoriteActor = favoriteActorRepository.findByUserAndActor(user, actor);

        if (favoriteActor == null) {
            // 이전에 저장된 선호 배우 정보가 없다면 가중치는 기본값 (-1 or 1)
            favoriteActor = FavoriteActor.builder()
                                         .user(user)
                                         .actor(actor)
                                         .weight(weight)
                                         .build();
        } else {
            // 이전에 저장된 선호 배우 정보가 있다면 가중치 추가
            favoriteActor.setWeight(weight + favoriteActor.getWeight());
        }

        favoriteActorRepository.save(favoriteActor);
    }

    @Override
    public List<FavActorRes> getFavoriteActorList(User user) {
        return favoriteActorRepository.findTopCountFavActorListByUser(12, user).orElse(null);
    }

    /**
     * 사용자의 선호 배우 top5 중 랜덤으로 두명을 선택해서 반환
     */
    @Override
    public List<Actor> getTwoFavoriteActorId(Long userId) {
        List<Actor> randomFavoriteActorIdList = new ArrayList<>();
        List<Actor> favoriteActorIdList = favoriteActorRepository.findTopCountByUserId(5, userId);
        Set<Integer> indexSet = new HashSet<>();

        // 선호 배우가 없다면 비어있는 리스트 반환
        if (favoriteActorIdList.size() == 0) {
            return randomFavoriteActorIdList;
        }

        // 선호 배우가 2명 이상이라면 랜덤으로 2명이 선택될 때까지 반복
        do {
            int index = (int) (Math.random() * favoriteActorIdList.size());
            indexSet.add(index);
        } while (favoriteActorIdList.size() >= 2 && indexSet.size() < 2);

        // 선택된 배우 정보 리스트에 추가
        for (int index : indexSet) {
            randomFavoriteActorIdList.add(favoriteActorIdList.get(index));
        }

        return randomFavoriteActorIdList;
    }
}
