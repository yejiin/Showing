package com.showing.backend.api.response;

import com.showing.backend.db.entity.performance.Season;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@ApiModel("SeasonResponse")
@Getter
@Setter
public class SeasonRes {

    @ApiModelProperty(name = "시즌별 공연 id", example = "1")
    Long seasonId;

    @ApiModelProperty(name = "시즌별 공연 이미지 경로", example = "http://k.kakaocdn.net/dn/")
    String seasonImage;

    @ApiModelProperty(name = "공연 시작일시", example = "2022-3-24")
    LocalDate startDate;

    @ApiModelProperty(name = "공연 종료일시", example = "2022-4-8")
    LocalDate endDate;

    @ApiModelProperty(name = "장소", example = "세종문화회관 대극장")
    String location;

    @ApiModelProperty(name = "공연 설명", example = "비밀과 거짓의 가면을 벗기다")
    String description;

    @ApiModelProperty(name = "세부 장르", example = "라이선스")
    String detailType;

    @ApiModelProperty(name = "관람 시간", example = "155분 (인터미션 : 20분)")
    String runingTime;

    @ApiModelProperty(name = "진행 여부", example = "1")
    Integer proceedFlag;

    @ApiModelProperty(name = "나이 제한", example = "15세 이상")
    String performanceAge;

    @ApiModelProperty(name = "배우 리스트")
    List<ActorRes> actorList;

    public static SeasonRes of(Season season, List<ActorRes> actors){
        SeasonRes res = new SeasonRes();
        res.setSeasonId(season.getId());
        res.setSeasonImage(season.getSeasonImage());
        res.setStartDate(season.getStartDate());
        res.setEndDate(season.getEndDate());
        res.setLocation(season.getLocation());
        res.setDescription(season.getDescription());
        res.setDetailType(season.getDetailType());
        res.setRuningTime(season.getRunningtime());
        res.setProceedFlag(season.getProceedFlag());
        res.setPerformanceAge(season.getPerformanceAge());
        res.setActorList(actors);

        return res;
    }
}
