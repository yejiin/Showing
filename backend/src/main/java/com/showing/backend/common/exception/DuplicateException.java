package com.showing.backend.common.exception;

import com.showing.backend.common.exception.handler.ErrorCode;

public class DuplicateException extends BusinessException {
    public DuplicateException() {
        super(ErrorCode.ENTITY_NOT_FOUND);
    }
}
