package com.yoonje.authenticationexample.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoonje.authenticationexample.common.controller.ControllerTest;
import com.yoonje.authenticationexample.member.dto.member.response.MemberDeleteResponse;
import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static com.yoonje.authenticationexample.member.controller.MemberController.MEMBER_API_URI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MemberControllerTest extends ControllerTest {

    @Test
    @WithMockUser(authorities = "USER")
    @DisplayName("내 정보 조회 테스트")
    public void 내_정보_조회_테스트() throws Exception{
        // given
        User mockUser = new User("test@test.com", "Qwer!234", Collections.singletonList(new SimpleGrantedAuthority("USER")));
        MemberProfileResponse response = new MemberProfileResponse("test@test.com", "테스트 유저", 20);

        when(memberService.memberProfile(any(User.class))).thenReturn(response);

        // when
        MvcResult result = performGetRequest(mockMvc, MEMBER_API_URI + "/me", mockUser)
                .andReturn();

        // then
        String contentAsString = result.getResponse().getContentAsString();
        MemberProfileResponse actualResponse = objectMapper.readValue(contentAsString, MemberProfileResponse.class);

        assertThat(actualResponse.email()).isEqualTo(response.email());
        assertThat(actualResponse.age()).isEqualTo(response.age());

        verify(memberService).memberProfile(any(User.class));
    }

    @Test
    @WithMockUser(authorities = "USER")
    @DisplayName("회원 탈퇴 테스트")
    public void 회원_탈퇴_테스트() throws Exception{
        // given
        User mockUser = new User("test@test.com", "Qwer!234", Collections.singletonList(new SimpleGrantedAuthority("USER")));
        MemberDeleteResponse response = new MemberDeleteResponse(true);

        when(memberService.memberDelete(any(User.class))).thenReturn(response);

        // when
        MvcResult result = performDeleteRequest(mockMvc, MEMBER_API_URI, mockUser)
                .andReturn();

        // then
        String contentAsString = result.getResponse().getContentAsString();
        MemberDeleteResponse actualResponse = objectMapper.readValue(contentAsString, MemberDeleteResponse.class);

        assertThat(actualResponse.result()).isEqualTo(response.result());

        verify(memberService).memberDelete(any(User.class));
    }
}
