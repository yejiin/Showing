package com.showing.backend.api.service;

import com.showing.backend.api.request.AddRatingReq;
import com.showing.backend.api.request.ModifyRatingReq;
import com.showing.backend.api.response.RatingRes;
import com.showing.backend.common.exception.InvalidException;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.db.entity.PerformanceTag;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Performance;
import com.showing.backend.db.entity.performance.StarPoint;
import com.showing.backend.db.repository.PerformanceTagRepository;
import com.showing.backend.db.repository.UserRepository;
import com.showing.backend.db.repository.performance.PerformanceRepository;
import com.showing.backend.db.repository.performance.StarPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class RatingServiceImpl implements RatingService {

    private final TagService tagService;

    private final UserRepository userRepository;
    private final PerformanceRepository performanceRepository;
    private final StarPointRepository starPointRepository;
    private final PerformanceTagRepository performanceTagRepository;

    /*
     * 별점 추가
     */
    @Override
    public void addRating(AddRatingReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Performance performance = performanceRepository.findById(req.getPerformanceId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.PERFORMANCE_NOT_FOUND));

        // 해당 유저의 공연 평점이 이미 존재하면 exception
        starPointRepository.findByUser_IdAndPerformance_Id(user.getId(), performance.getId()).ifPresent(s -> {
            throw new InvalidException(ErrorCode.RATING_DUPLICATE_VALUE);
        });

        int rating = req.getRating();
        if (rating <= 0 || rating > 10)
            throw new InvalidException(ErrorCode.RATING_INVALID_VALUE);

        // 별점이 3 이상 이면 선호 태그 가중치 변경
        if (rating >= 6) {

            // 해당 공연의 태그를 가져온다
            List<PerformanceTag> performanceTagList = performanceTagRepository.findAllByPerformance(performance);
            for (PerformanceTag tag : performanceTagList) {
                // 가중치 더해주기
                tagService.setFavoriteTagWeight(user, tag.getTag(), tag.getWeight());
            }

        }
        StarPoint starPoint = StarPoint.builder()
                .user(user)
                .performance(performance)
                .rating(rating)
                .build();
        starPointRepository.save(starPoint);
    }

    /*
     * 별점 수정
     */
    @Override
    public void modifyRating(ModifyRatingReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        StarPoint starPoint = starPointRepository.findById(req.getStarId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.RATING_NOT_FOUND));

        int rating = req.getRating();
        if (rating <= 0 || rating > 10)
            throw new InvalidException(ErrorCode.RATING_INVALID_VALUE);

        int preRating = starPoint.getRating();

        // 별점이 3점 위로 올랐다면 선호태그 가중치를 더해준다.
        if (rating >= 6 && preRating < 6) {
            List<PerformanceTag> performanceTagList = performanceTagRepository
                    .findAllByPerformance(starPoint.getPerformance());
            for (PerformanceTag tag : performanceTagList) {
                tagService.setFavoriteTagWeight(starPoint.getUser(), tag.getTag(), tag.getWeight());
            }
        }
        // 별점이 3점 아래로 떨어졌다면 선호태그 가중치를 빼준다.
        else if (rating < 6 && preRating >= 6) {
            List<PerformanceTag> performanceTagList = performanceTagRepository
                    .findAllByPerformance(starPoint.getPerformance());
            for (PerformanceTag tag : performanceTagList) {
                tagService.setFavoriteTagWeight(starPoint.getUser(), tag.getTag(), tag.getWeight() * -1);
            }
        }

        starPoint.setRating(rating);
    }

    /*
     * 별점 삭제
     */
    @Override
    public void deleteRating(Long starId) {
        StarPoint starPoint = starPointRepository.findById(starId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RATING_NOT_FOUND));

        if (starPoint.getRating() >= 6) {

            // 해당 공연의 태그를 가져온다
            List<PerformanceTag> performanceTagList = performanceTagRepository
                    .findAllByPerformance(starPoint.getPerformance());
            for (PerformanceTag tag : performanceTagList) {
                // 가중치 빼주기
                tagService.setFavoriteTagWeight(starPoint.getUser(), tag.getTag(), tag.getWeight() * -1);
            }

        }
        starPointRepository.delete(starPoint);
    }

    /*
     * 별점 조회
     */
    @Override
    public RatingRes getRating(Long userId, Long performanceId) {
        StarPoint starPoint = starPointRepository.findByUser_IdAndPerformance_Id(userId, performanceId).orElse(null);
        RatingRes ratingRes;
        if (starPoint == null) {
            ratingRes = RatingRes.builder()
                    .starId((long)0)
                    .rating(0)
                    .build();
        }else{
            ratingRes = RatingRes.builder()
                    .starId(starPoint.getId())
                    .rating(starPoint.getRating())
                    .build();
        }
        return ratingRes;
    }

    /*
     * 공연 타입 별 평가 수 조회
     */
    @Override
    public Long getRatingCount(User user, int type) {

        return starPointRepository.getRatingCountByUserAndType(user, type);
    }

    /*
     * 유저 별 별점 분포 수 ( 별점 rating 당 count )
     */
    @Override
    public List<Long> getRatingRatio(User user) {
        List<Long> countList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            countList.add(starPointRepository.getRatingCountByUserAndRating(user, i));
        }

        return countList;
    }

    /*
     * 별점 평균
     */
    public Double getRatingAvg(User user) {
        return starPointRepository.getRatingAvgByUser(user);
    }

}
