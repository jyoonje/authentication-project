package com.yoonje.authenticationexample.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoonje.authenticationexample.admin.service.AdminService;
import com.yoonje.authenticationexample.member.controller.AdminControllerTest;
import com.yoonje.authenticationexample.member.controller.MemberController;
import com.yoonje.authenticationexample.member.controller.SignController;
import com.yoonje.authenticationexample.member.repository.MemberRepository;
import com.yoonje.authenticationexample.member.service.MemberService;
import com.yoonje.authenticationexample.member.service.SignService;
import com.yoonje.authenticationexample.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ MemberController.class, SignController.class, AdminControllerTest.class})
// Web Layer에 속한 항목들: Security, Filter, Interceptor, request/response Handling, Controller
public class ControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected PasswordEncoder encoder;
    @MockBean
    protected TokenProvider tokenProvider;
    @MockBean
    protected SignService signService;
    @MockBean
    protected MemberService memberService;
    @MockBean
    protected AdminService adminService;
    @MockBean
    protected MemberRepository memberRepository;

    public ResultActions performPostRequest(MockMvc mockMvc, String url, Object requestBody) throws Exception {
        return mockMvc.perform(post(url)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk());
    }

    public ResultActions performGetRequest(MockMvc mockMvc, String url, Object user) throws Exception {
        return mockMvc.perform(get(url)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    public ResultActions performDeleteRequest(MockMvc mockMvc, String url, Object user) throws Exception {
        return mockMvc.perform(delete(url)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

}
