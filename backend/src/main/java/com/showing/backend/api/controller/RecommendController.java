package com.showing.backend.api.controller;

import com.showing.backend.api.service.RecommendService;
import com.showing.backend.common.auth.JwtUtil;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.common.exception.handler.ErrorResponse;
import com.showing.backend.common.model.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.showing.backend.common.model.ResponseMessage.GET_RECOMMEND;

@Api(value = "공연 추천 API", tags = {"recommend"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @ApiOperation(value = "사용자 맞춤 추천 공연 목록 조회",
            notes = "사용자가 선호하는 상위 5명 배우 중 한명의 출연 공연 목록, 별점 4점 이상 평가한 공연과 비슷한 공연들을 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_RECOMMEND),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/users/{userId}")
    public ResponseEntity<BaseResponseBody> getRecommendPerformanceList(@PathVariable Long userId) {
        // userId 유효성 검사
        if (userId == null || !Objects.equals(userId, JwtUtil.getCurrentId().orElse(null))) {
            throw new AccessDeniedException(ErrorCode.ACCESS_DENIED.getMessage());
        }

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_RECOMMEND, recommendService.getRecommendList(userId)));
    }

    @ApiOperation(value = "공연 중인 공연 목록 조회",
        notes = "로그인 안했을 때 보여주는 추천 데이터로, 공연 중인 공연 목록를 랜덤으로 15개 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_RECOMMEND),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("")
    public ResponseEntity<BaseResponseBody> getPerformingList() {
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_RECOMMEND, recommendService.getPerformingList()));
    }

}
