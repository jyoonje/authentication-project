package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.member.dto.sign_up.request.SignUpRequest;

public interface SignService {
    void signUp(SignUpRequest request);
    void signIn(SignUpRequest request);


}
