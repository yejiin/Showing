package com.showing.backend.api.controller;

import com.showing.backend.api.request.ReviewReq;
import com.showing.backend.api.service.ReviewService;
import com.showing.backend.common.auth.JwtUtil;
import com.showing.backend.common.exception.handler.ErrorResponse;
import com.showing.backend.common.model.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import static com.showing.backend.common.model.ResponseMessage.ADD_REVIEW;
import static com.showing.backend.common.model.ResponseMessage.GET_REVIEW;

@Api(value = "리뷰 API", tags = {"review"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = "사용자별 리뷰 조회", notes = "사용자가 작성한 리뷰 목록을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/users/{userId}")
    public ResponseEntity<BaseResponseBody> ListReviewByUser(@PathVariable Long userId) {
        // userId 유효성 검사
        if (userId.equals(JwtUtil.getCurrentId().orElse(-1L))) {
            throw new AccessDeniedException("Access denied");
        }

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_REVIEW, reviewService.getReviewListByUserId(userId)));
    }

    @ApiOperation(value = "리뷰 상세 조회", notes = "리뷰 상세 정보를 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/{reviewId}")
    public ResponseEntity<BaseResponseBody> DetailReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_REVIEW, reviewService.getReviewDetail(reviewId)));
    }

    @ApiOperation(value = "리뷰 추가", notes = "리뷰를 작성합니다.")
    @ApiResponses({@ApiResponse(code = 201, message = ADD_REVIEW),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @PostMapping("/users/{userId}")
    public ResponseEntity<BaseResponseBody> addReview(@PathVariable Long userId, @RequestBody ReviewReq req) {
        // userId 유효성 검사
        if (userId.equals(JwtUtil.getCurrentId()
                                 .orElse(-1L))) {
            throw new AccessDeniedException("Access denied");
        }

        reviewService.addReview(userId, req);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.CREATED, ADD_REVIEW));
    }

}
