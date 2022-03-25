package com.showing.backend.api.service;

import com.showing.backend.api.response.ActorRes;
import com.showing.backend.api.response.SeasonRes;
import com.showing.backend.common.exception.SeasonNotFoundException;
import com.showing.backend.db.entity.performance.Season;
import com.showing.backend.db.repository.performance.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class SeasonServiceImpl implements SeasonService{

    private final SeasonRepository seasonRepository;
    private final ActorService actorService;

    @Override
    public SeasonRes getSeasonInfo(Long seasonId) {
        Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new SeasonNotFoundException());
        List<ActorRes> actorResList = actorService.getSeasonCastingList(season.getPlaydbId());
        return SeasonRes.of(season, actorResList);
    }
}
