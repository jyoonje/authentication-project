package com.yoonje.authenticationexample.dto.sign_up.response;

import com.yoonje.authenticationexample.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record SignUpResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        UUID id,
        @Schema(description = "회원 아이디", example = "yoonje1234")
        String account,
        @Schema(description = "회원 이름", example = "윤제")
        String name,
        @Schema(description = "회원 나이", example = "27")
        Integer age
) {
    public static SignUpResponse from(Member member) {
        return new SignUpResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getAge()
        );
    }
}