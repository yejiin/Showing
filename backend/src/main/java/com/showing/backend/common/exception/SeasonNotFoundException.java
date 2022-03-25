package com.showing.backend.common.exception;

import com.showing.backend.common.exception.handler.ErrorCode;

public class SeasonNotFoundException extends NotFoundException {

    public SeasonNotFoundException() {
        super(ErrorCode.SEASON_NOT_FOUND);
    }

}