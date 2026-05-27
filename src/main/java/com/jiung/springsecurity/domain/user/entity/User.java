package com.jiung.springsecurity.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //null 금지 , 중복 금지
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreatedDate                              // ← 추가
    private LocalDateTime createdAt;

    @LastModifiedDate                         // ← 추가
    private LocalDateTime updatedAt;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
