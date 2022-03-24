package com.showing.backend.common.exception;

import com.showing.backend.common.exception.handler.ErrorCode;

public class InvalidException extends BusinessException {
    public InvalidException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
