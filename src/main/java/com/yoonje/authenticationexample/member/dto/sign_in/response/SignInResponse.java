package com.yoonje.authenticationexample.member.dto.sign_in.response;

import com.yoonje.authenticationexample.common.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignInResponse(
        @Schema(description = "회원 이름", example = "윤제")
        String name,
        @Schema(description = "회원 유형", example = "USER")
        MemberType type,
        String token
) {
}