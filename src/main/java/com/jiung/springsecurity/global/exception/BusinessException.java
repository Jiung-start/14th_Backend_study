package com.jiung.springsecurity.global.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BusinessException extends RuntimeException{

    private final ErrorCode errorCode;

    public ErrorCode
    getErrorCode() {
        return errorCode;
    }
}
