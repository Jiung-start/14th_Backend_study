package com.jiung.springsecurity.global.exception;

public enum ErrorCode {
    //가입되지 않은 이메일 일때 방생
    USER_NOT_FOUND("가입되지 않은 이메일입니다."),

    //비번 틀렸을때
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),

    //이미 존재하는 이메일로 시도
    DUPLICATE_EMAIL("이미 존재하는 이메일입니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
