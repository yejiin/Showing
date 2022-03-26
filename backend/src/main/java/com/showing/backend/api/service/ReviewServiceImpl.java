package com.showing.backend.api.service;

import com.showing.backend.api.request.ReviewReq;
import com.showing.backend.api.response.ReviewByUserPreviewRes;
import com.showing.backend.api.response.ReviewDetailRes;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.*;
import com.showing.backend.db.entity.review.Review;
import com.showing.backend.db.entity.review.ReviewActor;
import com.showing.backend.db.repository.UserRepository;
import com.showing.backend.db.repository.performance.CastingRepository;
import com.showing.backend.db.repository.performance.SeasonRepository;
import com.showing.backend.db.repository.review.ReviewActorRepository;
import com.showing.backend.db.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final SeasonRepository seasonRepository;
    private final CastingRepository castingRepository;
    private final ReviewActorRepository reviewActorRepository;

    @Override
    public List<ReviewByUserPreviewRes> getReviewListByUserId(Long userId) {
        List<Review> reviewList = reviewRepository.findByUserId(userId);
        List<ReviewByUserPreviewRes> reviewPreviewList = new ArrayList<>();

        for (Review review : reviewList) {
            Performance performance = review.getSeason().getPerformance();

            ReviewByUserPreviewRes reviewPreview = ReviewByUserPreviewRes.builder()
                                                                         .reviewId(review.getId())
                                                                         .userId(userId)
                                                                         .performanceId(performance.getId())
                                                                         .performanceName(performance.getPerformanceName())
                                                                         .performanceImage(performance.getPerformanceImage())
                                                                         .reviewCreateDate(review.getCreateDate().toLocalDate())
                                                                         .build();

            reviewPreviewList.add(reviewPreview);
        }

        return reviewPreviewList;
    }

    @Override
    public ReviewDetailRes getReviewDetail(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));
        Season season = review.getSeason();
        Performance performance = season.getPerformance();

        // 리뷰 id로 리뷰에 작성되어있는 캐스팅 정보를 조회한다.
        List<ReviewActor> reviewActorList = reviewActorRepository.findReviewActorsByReviewId(reviewId);

        // 리뷰에 작성되어있는 캐스팅 정보에서 배우 이름을 추출한다.
        List<String> reviewActorNameList = new ArrayList<>();
        for (ReviewActor reviewActor : reviewActorList) {
            reviewActorNameList.add(reviewActor.getCasting().getActor().getActorName());
        }

        ReviewDetailRes reviewDetailRes = ReviewDetailRes.builder()
                                                         .reviewId(reviewId)
                                                         .userId(review.getUser().getId())
                                                         .userName(review.getUser().getNickname())
                                                         .performanceId(performance.getId())
                                                         .performanceName(performance.getPerformanceName())
                                                         .seasonId(season.getId())
                                                         .startDate(season.getStartDate())
                                                         .endDate(season.getEndDate())
                                                         .viewDate(review.getPerformanceDate().toLocalDate())
                                                         .viewTime(review.getPerformanceDate().toLocalTime())
                                                         .location(season.getLocation())
                                                         .reviewActorNameList(reviewActorNameList)
                                                         .content(review.getReviewContent())
                                                         .reviewCreateDate(review.getCreateDate())
                                                         .build();

        return reviewDetailRes;
    }

    @Override
    public void addReview(Long userId, ReviewReq req) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Season season = seasonRepository.findById(req.getSeasonId()).orElseThrow(() -> new NotFoundException(ErrorCode.SEASON_NOT_FOUND));

        List<Long> castingIdList = req.getCastingIdList();
        List<ReviewActor> reviewActorList = new ArrayList<>();

        Review review = Review.builder().user(user).season(season).performanceDate(req.getShowDate().atTime(req.getShowTime()))
                              .reviewContent(req.getReviewContent())
                              .build();
        reviewRepository.save(review);

        for (Long castingId : castingIdList) {
            Casting casting = castingRepository.findById(castingId).orElseThrow(() -> new NotFoundException(ErrorCode.CASTING_NOT_FOUND));
            ReviewActor reviewActor = new ReviewActor(review, casting);
            reviewActorList.add(reviewActor);
        }

        reviewActorRepository.saveAll(reviewActorList);
    }

}

