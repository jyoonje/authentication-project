package com.yoonje.authenticationexample.dto.sign_in.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInRequest(
        @Schema(description = "회원 아이디", example = "yoonje1234")
        String email,
        @Schema(description = "회원 비밀번호", example = "1234")
        String password
) {

}