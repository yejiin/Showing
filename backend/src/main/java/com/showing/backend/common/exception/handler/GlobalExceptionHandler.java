package com.showing.backend.common.exception.handler;

import com.showing.backend.common.exception.*;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Runtime Exception 처리
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(ErrorResponse.of(errorCode));
    }

    /**
     * Value Duplicate Exception 처리
     */
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> duplicateException(DuplicateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(ErrorCode.DUPLICATE_INPUT_VALUE));
    }

    /**
     * Value Invalid Exception 처리
     */
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorResponse> invalidException(InvalidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE));
    }

    /**
     * Entity Not Found Exception 처리
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(ErrorCode.ENTITY_NOT_FOUND));
    }

}
