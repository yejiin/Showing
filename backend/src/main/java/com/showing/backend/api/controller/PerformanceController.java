package com.showing.backend.api.controller;

import com.showing.backend.api.response.MainPerformanceListRes;
import com.showing.backend.api.response.PerformanceRes;
import com.showing.backend.api.service.PerformanceService;
import com.showing.backend.api.service.SeasonService;
import com.showing.backend.common.auth.JwtUtil;
import com.showing.backend.common.exception.InvalidException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.common.exception.handler.ErrorResponse;
import com.showing.backend.common.model.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.showing.backend.common.model.ResponseMessage.*;

@Api(value = "공연 API", tags = {"performance"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/performances")
public class PerformanceController {

    private final PerformanceService performanceService;
    private final SeasonService seasonService;

    @ApiOperation(value = "공연 정보", notes = "공연의 정보를 보여준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = GET_PERFORMANCE),
            @ApiResponse(code = 400, message = "실패", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "인증 실패", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    @GetMapping("/{performanceId}")
    public ResponseEntity<BaseResponseBody> getPerformanceDetail(
            @PathVariable("performanceId") @ApiParam(value = "확인할 공연의 id", required = true) Long performanceId){

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_PERFORMANCE, performanceService.getPerformanceDetail(performanceId)));
    }

    @ApiOperation(value = "퍼포먼스 별 시즌 목록", notes = "시즌의 목록을 보여준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = GET_SEASON_LIST, response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "실패", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "인증 실패", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    @GetMapping("/seasons/{performanceId}")
    public ResponseEntity<BaseResponseBody> getSeasonList(
            @PathVariable("performanceId") @ApiParam(value = "목록을 얻을 퍼포먼스의 id", required = true) Long performanceId){

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_SEASON_LIST, seasonService.getSeasonList(performanceId)));
    }


    @ApiOperation(value = "시즌별 공연 정보", notes = "시즌별 공연의 정보를 보여준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = GET_SEASON),
            @ApiResponse(code = 400, message = "실패", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "인증 실패", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    @GetMapping("/seasons/list/{seasonId}")
    public ResponseEntity<BaseResponseBody> getSeasonDetail(
            @PathVariable("seasonId") @ApiParam(value = "확인할 공연 시즌의 id", required = true) Long seasonId){

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_SEASON, seasonService.getSeasonInfo(seasonId)));
    }


    @ApiOperation(value = "뮤지컬 평점랭킹, 연극 평점랭킹 목록", notes = "메인화면에 필요한 뮤지컬, 연극 정보를 보여준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = GET_PERFORMANCE_LIST),
            @ApiResponse(code = 400, message = "실패", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "인증 실패", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    @GetMapping("/ranking")
    public ResponseEntity<BaseResponseBody> getMainRatingAvgList(){

        List<PerformanceRes> musicalList = performanceService.getPerformanceListByStarPointAvg(1);
        List<PerformanceRes> playList = performanceService.getPerformanceListByStarPointAvg(2);

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_PERFORMANCE_LIST, new MainPerformanceListRes(musicalList, playList)));
    }
}
