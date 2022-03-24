package com.showing.backend.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 */
@ApiModel("BaseResponseBody")
@Getter
@Setter
public class BaseResponseBody {
    @ApiModelProperty(name = "응답 메시지", example = "정상")
    String message = null;
    @ApiModelProperty(name = "응답 코드", example = "200")
    Integer statusCode = null;
    @ApiModelProperty(name = "응답 데이터")
    Object data = null;

    public BaseResponseBody() {
    }

    public BaseResponseBody(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public BaseResponseBody(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static BaseResponseBody of(Integer statusCode, String message) {
        BaseResponseBody body = new BaseResponseBody();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }

    public static BaseResponseBody of(HttpStatus httpStatus, String message) {
        BaseResponseBody body = new BaseResponseBody();
        body.message = message;
        body.statusCode = changeHttpStatusToInt(httpStatus);
        return body;
    }

    public static BaseResponseBody of(HttpStatus httpStatus, String message, Object data) {
        BaseResponseBody body = new BaseResponseBody();
        body.message = message;
        body.statusCode = changeHttpStatusToInt(httpStatus);
        body.data = data;
        return body;
    }

    private static Integer changeHttpStatusToInt(HttpStatus httpStatus) {
        return Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
    }
}
