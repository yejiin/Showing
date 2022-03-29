package com.showing.backend.api.controller;

import com.showing.backend.api.service.RecommendService;
import com.showing.backend.common.exception.handler.ErrorResponse;
import com.showing.backend.common.model.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.showing.backend.common.model.ResponseMessage.GET_RECOMMEND;

@Api(value = "공연 추천 API", tags = {"recommend"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @ApiOperation(value = "비슷한 공연 목록 조회", notes = "한 공연에 대해 비슷한 공연 목록을 15개 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_RECOMMEND),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/{performanceId}")
    public ResponseEntity<BaseResponseBody> getRecommendPerformanceList(@PathVariable Long performanceId) {
        List<Long> performanceIdList = new ArrayList<>();
        performanceIdList.add(performanceId);

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_RECOMMEND, recommendService.getRecommendPerformanceList(0, performanceIdList)));
    }

    @ApiOperation(value = "사용자 추천 공연 목록 조회", notes = "사용자가 별점을 4점 이상 평가한 공연들에 대해 비슷한 공연 목록을 15개 조회합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = GET_RECOMMEND),
            @ApiResponse(code = 400, message = "Invalid Input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)})
    @GetMapping("/users/{userId}")
    public ResponseEntity<BaseResponseBody> getRecommendPerformanceListByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_RECOMMEND, recommendService.getRecommendPerformanceListByUser(userId, 8)));
    }

}
