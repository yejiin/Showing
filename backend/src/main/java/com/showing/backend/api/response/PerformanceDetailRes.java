package com.showing.backend.api.response;

import com.showing.backend.db.entity.performance.Performance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("PerformanceResponse")
@Getter
@Setter
public class PerformanceDetailRes {

    @ApiModelProperty(name = "공연 id", example = "1")
    Long performanceId;

    @ApiModelProperty(name = "공연 이름", example = "데스노트")
    String performanceName;

    @ApiModelProperty(name = "공연 이미지", example = "http://k.kakaocdn.net/dn/")
    String performanceImage;

    @ApiModelProperty(name = "공연 종류", example = "1")
    int performanceType;

    @ApiModelProperty(name = "공연 평균 별점", example = "5.5")
    double starPointAverage;

    @ApiModelProperty(name = "공연 평가 인원", example = "50")
    int ratingCount;

    @ApiModelProperty(name = "공연 최근 시즌 정보")
    SeasonRes seasonRes;

    @ApiModelProperty(name = "공연 리뷰 목록 미리보기")
    List<PreviewReviewByPerformanceRes> previewReviewList;

    public static PerformanceDetailRes of(Performance performance, RankingRes rankingRes, SeasonRes seasonRes, List<PreviewReviewByPerformanceRes> previewReviewList){
        PerformanceDetailRes res = new PerformanceDetailRes();
        res.setPerformanceId(performance.getId());
        res.setPerformanceName(performance.getPerformanceName());
        res.setPerformanceImage(performance.getPerformanceImage());
        res.setPerformanceType(performance.getPerformanceType());
        res.setRatingCount(rankingRes.getRatingCount());
        res.setStarPointAverage(rankingRes.getAverageRating());
        res.setSeasonRes(seasonRes);
        res.setPreviewReviewList(previewReviewList);

        return res;
    }
}
