package com.yoonje.authenticationexample.member.dto.member.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberProfileResponse(
        @Schema(description = "회원 이메일", example = "exam@exam.com")
        String email,
        @Schema(description = "회원 이름", example = "정윤제")
        String name,
        @Schema(description = "회원 나이", example = "20")
        Integer age

) {

}
