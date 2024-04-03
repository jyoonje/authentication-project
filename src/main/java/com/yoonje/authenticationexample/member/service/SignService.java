package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.member.dto.sign_in.request.SignInRequest;
import com.yoonje.authenticationexample.member.dto.sign_in.response.SignInResponse;
import com.yoonje.authenticationexample.member.dto.sign_up.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface SignService {
    void signUp(SignUpRequest request);
    SignInResponse signIn(SignInRequest request);


}
