package com.jiung.springsecurity.domain.user.dto;

import com.jiung.springsecurity.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupResponse {

    private Long id;
    private String email;

    public SignupResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }


}
