package com.yoonje.authenticationexample.dto.sign_up.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(
        @Schema(description = "회원 이메일", example = "exam123@naver.com")
        @NotEmpty
        @Email(message = "유효하지 않은 이메일 형식입니다.",
                regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
        String email,
        @Schema(description = "회원 비밀번호", example = "Qwer!234")
        @NotEmpty
        @Pattern(message = "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^&-+=()])(?=\\S+$).{8,16}$")
        String password,
        @Schema(description = "회원 이름", example = "윤제")
        @NotEmpty
        String name,
        @Schema(description = "회원 나이", example = "27")
        Integer age
) {
}