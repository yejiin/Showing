package com.showing.backend.common.exception.handler;

import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private Integer statusCode;
    private String message;
    private List<Error> errors;

    private ErrorResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    private ErrorResponse(ErrorCode code) {
        this.statusCode = code.getStatus();
        this.message = code.getMessage();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(ErrorCode code, List<Error> errors) {
        this.statusCode = code.getStatus();
        this.message = code.getMessage();
        this.errors = errors;
    }

    public static ErrorResponse of(int statusCode, String message) {
        return new ErrorResponse(statusCode, message);
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, Error.of(bindingResult));
    }

    public static ErrorResponse of(ErrorCode code, List<Error> errors) {
        return new ErrorResponse(code, errors);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Error {
        private String field;
        private String value;
        private String reason;

        private Error(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<Error> of(String field, String value, String reason) {
            List<Error> errors = new ArrayList<>();
            errors.add(new Error(field, value, reason));
            return errors;
        }

        public static List<Error> of(BindingResult bindingResult) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            return errors.stream().map(error -> new Error(error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
