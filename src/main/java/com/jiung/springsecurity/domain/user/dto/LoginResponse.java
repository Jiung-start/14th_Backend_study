package com.jiung.springsecurity.domain.user.dto;

import com.jiung.springsecurity.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Long id;

    private String email;

    private String token;


    public LoginResponse(User user , String token) {

        this.id = user.getId();
        this.email = user.getEmail();
        this.token = token;


    }
}
