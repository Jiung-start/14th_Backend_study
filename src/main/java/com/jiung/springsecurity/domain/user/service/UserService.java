package com.jiung.springsecurity.domain.user.service;

import com.jiung.springsecurity.domain.user.dto.LoginRequest;
import com.jiung.springsecurity.domain.user.dto.LoginResponse;
import com.jiung.springsecurity.domain.user.dto.SignupRequest;
import com.jiung.springsecurity.domain.user.dto.SignupResponse;
import com.jiung.springsecurity.domain.user.entity.User;
import com.jiung.springsecurity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public SignupResponse signup(SignupRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 가입된 이메일 입니다");

        }
        String encodePassword = bCryptPasswordEncoder.encode(request.getPassword());

        User user = new User(request.getEmail(), encodePassword);
        userRepository.save(user);

        return new SignupResponse(user);
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"가입되지 않은 이메일 입니다."));

        if(!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");

        }
        return new LoginResponse(user);
    }




}
