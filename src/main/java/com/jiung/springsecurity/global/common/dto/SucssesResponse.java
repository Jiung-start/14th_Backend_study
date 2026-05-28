package com.jiung.springsecurity.global.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SucssesResponse<T>{

    private final int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    private final String message;

    @Builder(access = AccessLevel.PRIVATE)
    private SucssesResponse(int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> SucssesResponse<T> ok(T data) {
        return SucssesResponse.<T>builder()
                .status(HttpStatus.OK.value())
                .message("요청성공")
                .data(data)
                .build();
    }



}
