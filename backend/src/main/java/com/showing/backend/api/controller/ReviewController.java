package com.showing.backend.api.controller;

import com.showing.backend.api.request.ReviewReq;
import com.showing.backend.api.service.ReviewService;
import com.showing.backend.common.auth.JwtUtil;
import com.showing.backend.common.exception.InvalidException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.common.exception.handler.ErrorResponse;
import com.showing.backend.common.model.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.showing.backend.common.model.ResponseMessage.*;

@Api(value = "리뷰 API", tags = {"review"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = "마이페이지 사용자별 리뷰 미리보기 목록 조회", notes = "(사용 x) 사용자가 작성한 리뷰 미리보기 목록을 수정 최신순으로 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/users/{userId}/preview")
    public ResponseEntity<BaseResponseBody> ListPreviewReviewByUser(@PathVariable Long userId) {
        // userId 유효성 검사
        if (userId == null) {
            throw new InvalidException(ErrorCode.INVALID_INPUT_VALUE);
        }

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_REVIEW, reviewService.getPreviewReviewListByUserId(userId)));
    }

    @ApiOperation(value = "공연별 리뷰 미리보기 조회", notes = "공연별로 리뷰 미리보기 목록을 수정 최신순으로 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/performances/{performanceId}")
    public ResponseEntity<BaseResponseBody> ListPreviewReviewByPerformance(@PathVariable Long performanceId) {
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_REVIEW, reviewService.getPreviewReviewListByPerformanceId(performanceId)));
    }

    @ApiOperation(value = "공연별 내 리뷰 미리보기 조회", notes = "공연별로 내 리뷰 미리보기 목록을 수정 최신순으로 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/performances/{performanceId}/{userId}")
    public ResponseEntity<BaseResponseBody> ListPreviewReviewByPerformance(@PathVariable Long performanceId, @PathVariable Long userId) {
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_REVIEW, reviewService.getReviewListByPerformanceIdAndUserId(performanceId, userId)));
    }

    @ApiOperation(value = "공연 시즌별 리뷰 전체 목록 조회", notes = "공연 시즌별로 리뷰 전체 목록을 수정 최신순으로 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/seasons/{seasonId}")
    public ResponseEntity<BaseResponseBody> ListReviewBySeason(@PathVariable Long seasonId) {
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_REVIEW, reviewService.getReviewListBySeasonId(seasonId)));
    }

    @ApiOperation(value = "리뷰 상세 조회", notes = "리뷰 상세 정보를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/{reviewId}")
    public ResponseEntity<BaseResponseBody> detailReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_REVIEW, reviewService.getReviewDetail(reviewId)));
    }

    @ApiOperation(value = "리뷰 추가", notes = "리뷰를 작성합니다.")
    @ApiResponses({@ApiResponse(code = 201, message = ADD_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @PostMapping("")
    public ResponseEntity<BaseResponseBody> addReview(@RequestBody ReviewReq req) {
        // userId 유효성 검사
        Long userId = req.getUserId();
        if (userId == null || !Objects.equals(userId, JwtUtil.getCurrentId())) {
            throw new AccessDeniedException(ErrorCode.ACCESS_DENIED.getMessage());
        }

        reviewService.addReview(req);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.CREATED, ADD_REVIEW));
    }

    @ApiOperation(value = "리뷰 수정", notes = "리뷰를 수정합니다.")
    @ApiResponses({@ApiResponse(code = 201, message = MODIFY_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @PutMapping("/{reviewId}")
    public ResponseEntity<BaseResponseBody> modifyReview(@PathVariable Long reviewId, @RequestBody ReviewReq req) {
        // userId 유효성 검사
        Long userId = req.getUserId();
        if (userId == null || !Objects.equals(userId, JwtUtil.getCurrentId())) {
            throw new AccessDeniedException(ErrorCode.ACCESS_DENIED.getMessage());
        }

        reviewService.modifyReview(reviewId, req);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.CREATED, MODIFY_REVIEW));
    }

    @ApiOperation(value = "리뷰 삭제", notes = "리뷰를 삭제합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = DELETE_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<BaseResponseBody> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, DELETE_REVIEW));
    }

}
