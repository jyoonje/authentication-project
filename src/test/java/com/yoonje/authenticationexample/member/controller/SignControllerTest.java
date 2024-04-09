package com.yoonje.authenticationexample.member.controller;

import com.yoonje.authenticationexample.common.controller.ControllerTest;
import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.dto.sign_in.request.SignInRequest;
import com.yoonje.authenticationexample.member.dto.sign_in.response.SignInResponse;
import com.yoonje.authenticationexample.member.dto.sign_up.request.SignUpRequest;
import com.yoonje.authenticationexample.member.service.SignService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static com.yoonje.authenticationexample.common.MemberType.USER;
import static com.yoonje.authenticationexample.member.controller.SignController.SIGN_API_URI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class SignControllerTest extends ControllerTest {

    @Test
    @WithMockUser
    void 회원가입_성공_테스트() throws Exception {
        // given
        SignUpRequest request = new SignUpRequest("test@test.com", "Qwer!234", "정윤제", 27);
        doNothing().when(signService).signUp(any(SignUpRequest.class));
        // when, then
        performPostRequest(mockMvc, SIGN_API_URI + "/sign-up", request);

        verify(signService).signUp(any(SignUpRequest.class));
    }


    @Test
    @WithMockUser
    @DisplayName("로그인 테스트")
    public void 로그인_성공_테스트() throws Exception {
                // given
        SignInRequest request = new SignInRequest("user@example.com", "password");
        SignInResponse response = new SignInResponse("userName", USER, "token");

        when(signService.signIn(any(SignInRequest.class))).thenReturn(response);

        // when, then
        performPostRequest(mockMvc, SIGN_API_URI + "/sign-in", request)
                .andExpect(jsonPath("$.name").value(response.name()))
                .andExpect(jsonPath("$.type").value(response.type().name()))
                .andExpect(jsonPath("$.token").value(response.token()));

        verify(signService).signIn(any(SignInRequest.class));
    }
}

