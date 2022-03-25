package com.showing.backend.api.service;

import com.showing.backend.api.request.AddRatingReq;
import com.showing.backend.common.exception.InvalidException;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.UserNotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Performance;
import com.showing.backend.db.entity.performance.StarPoint;
import com.showing.backend.db.repository.UserRepository;
import com.showing.backend.db.repository.performance.PerformanceRepository;
import com.showing.backend.db.repository.performance.StarPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final UserRepository userRepository;
    private final PerformanceRepository performanceRepository;
    private final StarPointRepository starPointRepository;

    /*
    별점 추가
     */
    @Override
    public void addRating(AddRatingReq req) {
        User user = userRepository.findById(req.getUserId()).orElseThrow(UserNotFoundException::new);
        Performance performance = performanceRepository.findById(req.getPerformanceId()).orElseThrow(()->new NotFoundException(ErrorCode.PERFORMANCE_NOT_FOUND));

        // 해당 유저의 공연 평점이 이미 존재하면 exception
        starPointRepository.findByUserAndPerformance(user,performance).ifPresent(s->{
            throw new InvalidException(ErrorCode.RATING_DUPLICATE_VALUE);
        });

        int rating = req.getRating();
        if(rating<=0||rating>10) throw new InvalidException(ErrorCode.RATING_INVALID_VALUE);

        StarPoint starPoint = StarPoint.builder()
                .user(user)
                .performance(performance)
                .rating(rating)
                .build();
        starPointRepository.save(starPoint);

    }
}
