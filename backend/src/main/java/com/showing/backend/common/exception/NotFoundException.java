package com.showing.backend.common.exception;

import com.showing.backend.common.exception.handler.ErrorCode;

import javax.persistence.EntityNotFoundException;

public class NotFoundException extends EntityNotFoundException {
    private ErrorCode errorCode;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
