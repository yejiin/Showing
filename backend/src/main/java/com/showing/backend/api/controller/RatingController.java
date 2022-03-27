package com.showing.backend.api.controller;

import com.showing.backend.api.request.AddRatingReq;
import com.showing.backend.api.request.UpdateRatingReq;
import com.showing.backend.api.service.RatingService;
import com.showing.backend.common.auth.JwtUtil;
import com.showing.backend.common.exception.InvalidException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.common.exception.handler.ErrorResponse;
import com.showing.backend.common.model.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.showing.backend.common.model.ResponseMessage.*;

@Api(value = "공연 평가 API", tags = "Rating")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {

    private final RatingService ratingService;

    @ApiOperation(value = "별점 추가", notes = "공연 별점을 추가합니다.")
    @ApiResponses({@ApiResponse(code = 201, message = ADD_RATING),
            @ApiResponse(code = 401, message = UNAUTHORIZED, response = ErrorResponse.class),
            @ApiResponse(code = 403, message = FORBIDDEN, response = ErrorResponse.class),
            @ApiResponse(code = 404, message = NOT_FOUND, response = ErrorResponse.class),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)})
    @PostMapping(value="/")
    public ResponseEntity<BaseResponseBody> addRating(@RequestBody AddRatingReq req) {
        // userId 유효성 체크
        if(!Objects.equals(req.getUserId(), JwtUtil.getCurrentId().orElse(null)))
            throw new InvalidException(ErrorCode.ACCESS_DENIED);
        ratingService.addRating(req);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.CREATED, ADD_RATING));
    }

    @ApiOperation(value = "별점 수정", notes = "공연 별점을 수정합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = UPDATE_RATING),
            @ApiResponse(code = 401, message = UNAUTHORIZED, response = ErrorResponse.class),
            @ApiResponse(code = 403, message = FORBIDDEN, response = ErrorResponse.class),
            @ApiResponse(code = 404, message = NOT_FOUND, response = ErrorResponse.class),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)})
    @PutMapping(value = "/")
    public ResponseEntity<BaseResponseBody> updateRating(@RequestBody UpdateRatingReq req) {
        // userId 유효성 체크
        if(!Objects.equals(req.getUserId(), JwtUtil.getCurrentId().orElse(null)))
            throw new InvalidException(ErrorCode.ACCESS_DENIED);
        ratingService.updateRating(req);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, UPDATE_RATING));
    }

    @ApiOperation(value = "별점 삭제", notes = "공연 별점을 삭제합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = DELETE_RATING),
            @ApiResponse(code = 401, message = UNAUTHORIZED, response = ErrorResponse.class),
            @ApiResponse(code = 403, message = FORBIDDEN, response = ErrorResponse.class),
            @ApiResponse(code = 404, message = NOT_FOUND, response = ErrorResponse.class),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)})
    @DeleteMapping(value = "/{starId}")
    public ResponseEntity<BaseResponseBody> updateRating(@PathVariable Long starId) {
        ratingService.deleteRating(starId);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, DELETE_RATING));
    }

}
