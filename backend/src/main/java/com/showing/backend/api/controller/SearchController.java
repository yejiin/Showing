package com.showing.backend.api.controller;

import com.showing.backend.api.service.SearchService;
import com.showing.backend.common.exception.InvalidException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.common.exception.handler.ErrorResponse;
import com.showing.backend.common.model.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.showing.backend.common.model.ResponseMessage.GET_PERFORMANCE;

@Api(value = "검색 API", tags = {"search"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    @ApiOperation(value = "공연 검색", notes = "공연의 제목으로 검색한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = GET_PERFORMANCE),
            @ApiResponse(code = 400, message = "실패", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "인증 실패", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = ErrorResponse.class)
    })
    @GetMapping("")
    public ResponseEntity<BaseResponseBody> getContentsList(
            @RequestParam(value = "keyword") @ApiParam(value = "검색할 키워드", required = true) String keyword){
        //유효성 체크
        if("".equals(keyword.trim()))
            throw new InvalidException(ErrorCode.SEARCH_INVALID_VALUE);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK, GET_PERFORMANCE, searchService.getPerformanceList(keyword)));
    }
}
