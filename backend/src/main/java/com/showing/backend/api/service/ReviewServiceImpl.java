package com.showing.backend.api.service;

import com.showing.backend.api.request.AddReviewReq;
import com.showing.backend.common.exception.*;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Casting;
import com.showing.backend.db.entity.performance.Season;
import com.showing.backend.db.entity.review.Review;
import com.showing.backend.db.entity.review.ReviewActor;
import com.showing.backend.db.repository.UserRepository;
import com.showing.backend.db.repository.performance.CastingRepository;
import com.showing.backend.db.repository.performance.SeasonRepository;
import com.showing.backend.db.repository.review.ReviewActorRepository;
import com.showing.backend.db.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final SeasonRepository seasonRepository;
    private final CastingRepository castingRepository;
    private final ReviewActorRepository reviewActorRepository;

    @Override
    public void addReview(Long userId, AddReviewReq req) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Season season = seasonRepository.findById(req.getSeasonId()).orElseThrow(() -> new SeasonNotFoundException());
        List<Long> castingIdList = req.getCastingIdList();
        List<ReviewActor> reviewActorList = new ArrayList<>();

        Review review = Review.builder()
                              .user(user)
                              .season(season)
                              .performanceDate(req.getShowDate().atTime(req.getShowTime()))
                              .reviewContent(req.getReviewContent())
                              .build();
        reviewRepository.save(review);

        for (Long castingId : castingIdList) {
            Casting casting = castingRepository.findById(castingId).orElseThrow(() -> new CastingNotFoundException());
            ReviewActor reviewActor = new ReviewActor(review, casting);
            reviewActorList.add(reviewActor);
        }
        reviewActorRepository.saveAll(reviewActorList);
    }

}
