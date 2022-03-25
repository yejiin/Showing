package com.showing.backend.common.exception;

import com.showing.backend.common.exception.handler.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshInvalidException extends BusinessException {

    public RefreshInvalidException() { super(ErrorCode.REFRESH_TOKEN_INVALID);}
}
