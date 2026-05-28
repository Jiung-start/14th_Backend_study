package com.jiung.springsecurity.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    //에러 이름
    private String code;
    //실제 에러 메시지
    private String message;
}
