package com.yoonje.authenticationexample.member.entity;

import com.yoonje.authenticationexample.common.MemberType;
import com.yoonje.authenticationexample.member.dto.member.request.MemberUpdateRequest;
import com.yoonje.authenticationexample.member.dto.sign_up.request.SignUpRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.UUID;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
    @Column(nullable = false, scale = 30, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public static Member from(SignUpRequest request, PasswordEncoder encoder) {
        return Member.builder()
                .email(request.email())
                .password(encoder.encode(request.password()))
                .name(request.name())
                .age(request.age())
                .type(MemberType.USER)
                .build();
    }

    @Builder
    private Member(String email, String password, String name, Integer age, MemberType type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public void update(MemberUpdateRequest newMember, PasswordEncoder encoder) {
        this.password = newMember.newPassword() == null || newMember.newPassword().isBlank()
                ? this.password : encoder.encode(newMember.newPassword());
        this.name = newMember.name();
        this.age = newMember.age();
    }

}