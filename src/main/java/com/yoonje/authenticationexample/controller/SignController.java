package com.yoonje.authenticationexample.controller;

import com.yoonje.authenticationexample.dto.ApiResponse;
import com.yoonje.authenticationexample.dto.sign_in.request.SignInRequest;
import com.yoonje.authenticationexample.dto.sign_up.request.SignUpRequest;
import com.yoonje.authenticationexample.service.SignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yoonje.authenticationexample.controller.SignController.MEMBER_API_URI;

@Tag(name = "회원 가입 및 로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping(MEMBER_API_URI)
public class SignController {
    public static final String MEMBER_API_URI = "/api/members";

    private final SignService signService;

    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ApiResponse signUp(@Valid @RequestBody SignUpRequest request) {
        return ApiResponse.success(signService.registerMember(request));
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ApiResponse signIn(@Valid @RequestBody SignInRequest request) {
        return ApiResponse.success(signService.signIn(request));
    }

}
