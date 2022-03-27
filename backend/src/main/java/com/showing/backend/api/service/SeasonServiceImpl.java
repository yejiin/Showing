package com.showing.backend.api.service;

import com.showing.backend.api.response.ActorRes;
import com.showing.backend.api.response.SeasonDateRes;
import com.showing.backend.api.response.SeasonRes;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.entity.performance.Season;
import com.showing.backend.db.repository.performance.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class SeasonServiceImpl implements SeasonService{

    private final SeasonRepository seasonRepository;
    private final ActorService actorService;

    @Override
    public List<SeasonDateRes> getSeasonList(Long performanceId) {
        List<Season> seasonList = seasonRepository.findByPerformanceId(performanceId).orElseThrow(() -> new NotFoundException(ErrorCode.SEASON_NOT_FOUND));
        List<SeasonDateRes> seasonDateResList = new ArrayList<>();
        for (Season season : seasonList){

        }
        return null;
    }

    @Override
    public SeasonRes getSeasonInfo(Long seasonId) {
        Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new NotFoundException(ErrorCode.SEASON_NOT_FOUND));
        List<ActorRes> actorResList = actorService.getSeasonCastingList(season.getPlaydbId());
        return SeasonRes.of(season, actorResList);
    }
}
