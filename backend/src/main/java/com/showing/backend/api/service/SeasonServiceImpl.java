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
        // performanceId에 속하는 모든 season 목록
        List<Season> seasonList = seasonRepository.findByPerformanceIdOrderByStartDateDesc(performanceId);
        List<SeasonDateRes> seasonDateResList = new ArrayList<>();

        // 시즌별로 반복
        for (Season season : seasonList){
            SeasonDateRes seasonDateRes = SeasonDateRes.builder()
                    .seasonId(season.getId())
                    .startDate(season.getStartDate())
                    .endDate(season.getEndDate())
                    .build();
            seasonDateResList.add(seasonDateRes);
        }

        return seasonDateResList;
    }

    @Override
    public SeasonRes getSeasonInfo(Long seasonId) {
        // seasonId에 해당하는 season 정보
        Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new NotFoundException(ErrorCode.SEASON_NOT_FOUND));
        // 해당 season에 출연하는 캐스팅 배우 목록
        List<ActorRes> actorResList = actorService.getSeasonCastingList(season.getPlaydbId());

        return SeasonRes.of(season, actorResList);
    }
}
