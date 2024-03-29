package com.showing.backend.api.service;

import com.showing.backend.api.request.ReviewReq;
import com.showing.backend.api.response.*;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.*;
import com.showing.backend.db.entity.review.Review;
import com.showing.backend.db.entity.review.ReviewActor;
import com.showing.backend.db.repository.UserRepository;
import com.showing.backend.db.repository.performance.*;
import com.showing.backend.db.repository.review.ReviewActorRepository;
import com.showing.backend.db.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static com.showing.backend.common.exception.handler.ErrorCode.PERFORMANCE_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

    private final PerformanceRepository performanceRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final SeasonRepository seasonRepository;
    private final CastingRepository castingRepository;
    private final ReviewActorRepository reviewActorRepository;
    private final WordCloudRepository wordCloudRepository;

    private final ActorService actorService;

    @Override
    public List<PreviewReviewByUserRes> getPreviewReviewListByUserId(Long userId) {
        List<Review> reviewList = reviewRepository.findByUserIdOrderByUpdateDateDesc(userId);
        List<PreviewReviewByUserRes> previewReviewList = new ArrayList<>();

        for (Review review : reviewList) {
            Performance performance = review.getSeason().getPerformance();

            PreviewReviewByUserRes previewReview = PreviewReviewByUserRes.builder()
                                                                         .reviewId(review.getId())
                                                                         .performanceId(performance.getId())
                                                                         .performanceName(performance.getPerformanceName())
                                                                         .performanceType(performance.getPerformanceType())
                                                                         .seasonId(review.getSeason().getId())
                                                                         .performanceImage(performance.getPerformanceImage())
                                                                         .viewDate(review.getPerformanceDate().toLocalDate())
                                                                         .build();

            previewReviewList.add(previewReview);
        }

        return previewReviewList;
    }


    @Override
    public List<ReviewByUserRes> getReviewListByPerformanceIdAndUserId(Long performanceId, Long userId) {

        Performance performance = performanceRepository.findById(performanceId).orElseThrow(()->new NotFoundException(PERFORMANCE_NOT_FOUND));
        // userid 유저가 performanceId 공연에 작성한 리뷰 리스트
        List<Review> reviewList = reviewRepository.getReviewByPerformanceAndUser(performanceId,userId);
        List<ReviewActor> reviewActorList;
        List<String> reviewActorNameList;
        List<ReviewByUserRes> resList = new ArrayList<>();

        for (Review review : reviewList) {

            // 리뷰 id로 리뷰에 작성되어있는 캐스팅 정보를 조회한다.
            reviewActorList = reviewActorRepository.findReviewActorsByReviewId(review.getId());

            // 리뷰에 작성되어있는 캐스팅 정보에서 배우 이름을 추출한다.
            reviewActorNameList = new ArrayList<>();
            for (ReviewActor reviewActor : reviewActorList) {
                reviewActorNameList.add(reviewActor.getCasting().getActor().getActorName());
            }

            ReviewByUserRes reviewRes = ReviewByUserRes.builder()
                    .reviewId(review.getId())
                    .userNickName(review.getUser().getNickname())
                    .reviewActorNameList(reviewActorNameList)
                    .content(review.getReviewContent())
                    .viewDate(LocalDate.from(review.getPerformanceDate()))
                    .build();

            resList.add(reviewRes);
        }

        return resList;
    }

    @Override
    public List<PreviewReviewByPerformanceRes> getPreviewReviewListByPerformanceId(Long performanceId) {
        // performanceId 에 해당하는 모든 시즌
        List<Season> seasonList = seasonRepository.findByPerformanceIdOrderByStartDateDesc(performanceId);
        // seasonList 시즌들의 모든 리뷰
        List<Review> reviewList = reviewRepository.findBySeasonInOrderByUpdateDateDesc(seasonList);
        List<PreviewReviewByPerformanceRes> previewReviewList = new ArrayList<>();

        for (Review review : reviewList) {
            PreviewReviewByPerformanceRes previewReview
                    = PreviewReviewByPerformanceRes.builder()
                                                   .reviewId(review.getId())
                                                   .userId(review.getUser().getId())
                                                   .userName(review.getUser().getNickname())
                                                   .userImage(review.getUser().getUserImage())
                                                   .content(review.getReviewContent())
                                                   .build();

            previewReviewList.add(previewReview);
        }

        return previewReviewList;
    }

    @Override
    public List<ReviewBySeasonRes> getReviewListBySeasonId(Long seasonId) {
        List<Review> reviewList = reviewRepository.findAllBySeasonIdOrderByUpdateDateDesc(seasonId);
        List<ReviewBySeasonRes> reviewResList = new ArrayList<>();
        List<ReviewActor> reviewActorList;
        List<String> reviewActorNameList;

        for (Review review : reviewList) {
            // 작성한 리뷰 내 캐스팅 배우 목록
            reviewActorList = reviewActorRepository.findReviewActorsByReviewId(review.getId());
            // 작성한 리뷰 내 캐스팅 배우 이름 목록
            reviewActorNameList = new ArrayList<>();
            for (ReviewActor reviewActor : reviewActorList) {
                reviewActorNameList.add(reviewActor.getCasting().getActor().getActorName());
            }

            // 리뷰 작성자
            User user = review.getUser();
            // 리뷰를 작성한 시즌의 공연
            Performance performance = review.getSeason().getPerformance();

            ReviewBySeasonRes reviewRes = ReviewBySeasonRes.builder()
                                                           .reviewId(review.getId())
                                                           .userId(user.getId())
                                                           .userName(user.getNickname())
                                                           .userImage(user.getUserImage())
                                                           .performanceId(performance.getId())
                                                           .performanceName(performance.getPerformanceName())
                                                           .content(review.getReviewContent())
                                                           .castingActorNameList(reviewActorNameList)
                                                           .viewDate(review.getPerformanceDate().toLocalDate())
                                                           .build();

            reviewResList.add(reviewRes);
        }

        return reviewResList;
    }

    @Override
    public List<WordCloudRes> getWordCloud(Long performanceId) {
        List<WordCloud> wordCloudList = wordCloudRepository.findTop15ByPerformanceIdOrderByWeightDesc(performanceId);
        List<WordCloudRes> wordCloudResList = new LinkedList<>();

        for (WordCloud wordCloud : wordCloudList) {
            wordCloudResList.add(new WordCloudRes(wordCloud.getWord(), wordCloud.getWeight()));
        }

        return wordCloudResList;
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
        List<Long> reviewCastingIdList = new ArrayList<>();
        for (ReviewActor reviewActor : reviewActorList) {
            reviewCastingIdList.add(reviewActor.getCasting().getId());
            reviewActorNameList.add(reviewActor.getCasting().getActor().getActorName());
        }

        ReviewDetailRes reviewDetailRes = ReviewDetailRes.builder()
                                                         .reviewId(reviewId)
                                                         .userId(review.getUser().getId())
                                                         .userName(review.getUser().getNickname())
                                                         .userImage(review.getUser().getUserImage())
                                                         .performanceId(performance.getId())
                                                         .performanceName(performance.getPerformanceName())
                                                         .seasonId(season.getId())
                                                         .seasonImage(season.getSeasonImage())
                                                         .startDate(season.getStartDate())
                                                         .endDate(season.getEndDate())
                                                         .viewDate(review.getPerformanceDate().toLocalDate())
                                                         .viewTime(review.getPerformanceDate().toLocalTime())
                                                         .location(season.getLocation()).reviewActorNameList(reviewActorNameList)
                                                         .reviewCastingIdList(reviewCastingIdList)
                                                         .content(review.getReviewContent())
                                                         .reviewCreateDate(review.getCreateDate())
                                                         .build();

        return reviewDetailRes;
    }

    @Override
    public void addReview(ReviewReq req) {
        List<Long> castingIdList = req.getCastingIdList();
        List<ReviewActor> reviewActorList = new ArrayList<>();

        User user = userRepository.findById(req.getUserId()).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Season season = seasonRepository.findById(req.getSeasonId()).orElseThrow(() -> new NotFoundException(ErrorCode.SEASON_NOT_FOUND));

        Review review = Review.builder()
                              .user(user)
                              .season(season)
                              .performanceDate(req.getShowDate().atTime(req.getShowTime()))
                              .reviewContent(req.getReviewContent())
                              .build();
        reviewRepository.save(review);

        for (Long castingId : castingIdList) {
            Casting casting = castingRepository.findById(castingId).orElseThrow(() -> new NotFoundException(ErrorCode.CASTING_NOT_FOUND));
            ReviewActor reviewActor = new ReviewActor(review, casting);
            reviewActorList.add(reviewActor);

            // 선호 배우 가중치를 증가시킨다.
            Actor actor = casting.getActor();
            actorService.setFavoriteActorWeight(1, user, actor);
        }

        reviewActorRepository.saveAll(reviewActorList);
    }

    @Override
    public void modifyReview(Long reviewId, ReviewReq req) {
        List<Long> castingIdList = req.getCastingIdList();
        List<ReviewActor> reviewActorList;

        // 기존에 작성된 리뷰 정보를 가져와서 수정한다.
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));
        review.setPerformanceDate(req.getShowDate().atTime(req.getShowTime()));
        review.setReviewContent(req.getReviewContent());

        // 기존에 작성되어있던 캐스팅 배우 정보 삭제
        reviewActorList = reviewActorRepository.findReviewActorsByReviewId(reviewId);
        reviewActorRepository.deleteAll(reviewActorList);

        // 새로 작성된 캐스팅 배우 정보 추가
        reviewActorList = new ArrayList<>();
        for (Long castingId : castingIdList) {
            Casting casting = castingRepository.findById(castingId).orElseThrow(() -> new NotFoundException(ErrorCode.CASTING_NOT_FOUND));
            ReviewActor reviewActor = new ReviewActor(review, casting);
            reviewActorList.add(reviewActor);
        }
        reviewActorRepository.saveAll(reviewActorList);
    }

    @Override
    public void deleteReview(Long reviewId) {
        // 선호 배우 가중치를 감소시킨다.
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));
        User user = review.getUser();

        List<ReviewActor> reviewActorList = reviewActorRepository.findReviewActorsByReviewId(reviewId);
        for (ReviewActor reviewActor : reviewActorList) {
            Actor actor = reviewActor.getCasting().getActor();
            actorService.setFavoriteActorWeight(-1, user, actor);
        }

        // 리뷰 내 캐스팅 배우 정보 삭제
        reviewActorRepository.deleteAllByReviewId(reviewId);
        // 리뷰 삭제
        reviewRepository.deleteById(reviewId);
    }

}

