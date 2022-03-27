package com.showing.backend.db.repository.review;

import com.showing.backend.db.entity.review.ReviewActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewActorRepository extends JpaRepository<ReviewActor, Long> {

    /**
     * review_actor table 에서 reviewId 로 리뷰 내 캐스팅 정보를 조회한다.
     */
    List<ReviewActor> findReviewActorsByReviewId(Long reviewId);

    /**
     * review_actor table 에서 reviewId 인 리뷰 내 캐스팅 정보를 모두 삭제한다.
     */
    void deleteAllByReviewId(Long reviewId);

}
