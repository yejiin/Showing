package com.showing.backend.common.exception;


import com.showing.backend.common.exception.handler.ErrorCode;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
