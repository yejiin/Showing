package com.showing.backend.common.exception;

import com.showing.backend.common.exception.handler.ErrorCode;

public class CastingNotFoundException  extends NotFoundException {

    public CastingNotFoundException() {
        super(ErrorCode.CASTING_NOT_FOUND);
    }

}