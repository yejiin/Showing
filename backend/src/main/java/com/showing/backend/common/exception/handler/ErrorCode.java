package com.showing.backend.common.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    DUPLICATE_INPUT_VALUE(400, "C002", "Duplicate Input Value"),
    USER_NOT_FOUND(404, "C003", "User Not Found"),
    // Show
    SEASON_NOT_FOUND(404, "S001", "Season Not Found"),
    CASTING_NOT_FOUND(404, "S002", "Casting Not Found"),
    PERFORMANCE_NOT_FOUND(404,"S003", "Performance Not Found"),
    // User
    REFRESH_TOKEN_INVALID(403,"U001","Refresh token is invalid"),
    NOT_ALLOWED_APPROACH(403,"U002","Not Allowed Approach"),
    // Rating
    RATING_INVALID_VALUE(400,"R001","Invalid Rating Value"),
    RATING_DUPLICATE_VALUE(400,"R002","Already Rated Performance"),
    RATING_NOT_FOUND(404,"R003","Rating Not Found");

    private int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
