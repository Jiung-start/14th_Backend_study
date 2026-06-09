package com.jiung.springsecurity.domain.user.service;

import com.jiung.springsecurity.domain.user.dto.LoginRequest;
import com.jiung.springsecurity.domain.user.dto.LoginResponse;
import com.jiung.springsecurity.domain.user.dto.SignupRequest;
import com.jiung.springsecurity.domain.user.dto.SignupResponse;
import com.jiung.springsecurity.domain.user.entity.User;
import com.jiung.springsecurity.domain.user.repository.UserRepository;
import com.jiung.springsecurity.global.exception.BusinessException;
import com.jiung.springsecurity.global.exception.ErrorCode;
import com.jiung.springsecurity.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public SignupResponse signup(SignupRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);

        }
        String encodePassword = bCryptPasswordEncoder.encode(request.getPassword());

        User user = new User(request.getEmail(), encodePassword);
        userRepository.save(user);

        String token = jwtTokenProvider.createToken(
                user.getId(),
                user.getEmail()
        );

        return new SignupResponse(user,token);
    }


    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if(!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);

        }
        String token = jwtTokenProvider.createToken(
                user.getId(),
                user.getEmail()
        );


        return new LoginResponse(user, token);
    }




}
