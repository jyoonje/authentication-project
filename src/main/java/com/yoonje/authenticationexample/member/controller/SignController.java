package com.yoonje.authenticationexample.member.controller;

import com.yoonje.authenticationexample.member.dto.ApiResponse;
import com.yoonje.authenticationexample.member.dto.sign_in.request.SignInRequest;
import com.yoonje.authenticationexample.member.dto.sign_in.response.SignInResponse;
import com.yoonje.authenticationexample.member.dto.sign_up.request.SignUpRequest;
import com.yoonje.authenticationexample.member.service.GeneralSignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yoonje.authenticationexample.member.controller.SignController.MEMBER_API_URI;
import static com.yoonje.authenticationexample.common.HttpStatusResponseEntity.*;


@Tag(name = "회원 가입 및 로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping(MEMBER_API_URI)
public class SignController {
    public static final String MEMBER_API_URI = "/api/members";

    private final GeneralSignService signService;

    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> signUp(@Valid @RequestBody SignUpRequest request) {
        signService.signUp(request);
        return RESPONSE_OK;
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest request) {
        return ResponseEntity.ok(signService.signIn(request));
    }

}
