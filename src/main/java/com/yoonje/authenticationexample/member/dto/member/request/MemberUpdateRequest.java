package com.yoonje.authenticationexample.member.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberUpdateRequest(
        @Schema(description = "회원 비밀번호", example = "1234")
        String password,
        @Schema(description = "회원 새 비밀번호", example = "1234")
        String newPassword,
        @Schema(description = "회원 이름", example = "윤제")
        String name,
        @Schema(description = "회원 나이", example = "27")
        Integer age
) {
}
