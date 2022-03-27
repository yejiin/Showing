package com.showing.backend.api.service;

import com.showing.backend.api.response.ActorRes;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.repository.performance.CastingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActorServiceImpl implements ActorService{

    private final CastingRepository castingRepository;

    @Override
    public List<ActorRes> getSeasonCastingList(Long seasonPlaydbId) {

        // seasonPlaydbId에 해당하는 캐스팅 배우 목록
        return castingRepository.getCastingBySeason(seasonPlaydbId).orElseThrow(() -> new NotFoundException(ErrorCode.CASTING_NOT_FOUND));
    }
}
