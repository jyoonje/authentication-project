package com.yoonje.authenticationexample.admin.controller;

import com.yoonje.authenticationexample.admin.service.AdminService;
import com.yoonje.authenticationexample.common.MemberType;
import com.yoonje.authenticationexample.common.controller.ControllerTest;
import com.yoonje.authenticationexample.member.dto.member.response.MemberDeleteResponse;
import com.yoonje.authenticationexample.member.dto.member.response.MemberInfoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.yoonje.authenticationexample.admin.controller.AdminController.ADMIN_API_URI;
import static com.yoonje.authenticationexample.member.controller.MemberController.MEMBER_API_URI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminControllerTest extends ControllerTest {
    static List<MemberInfoResponse> response = Collections.singletonList(new MemberInfoResponse(UUID.randomUUID(),
            "test@test.com",
            "테스트 유저",
            27,
            MemberType.USER,
            LocalDateTime.now()));

    @Test
    @WithMockUser(authorities = "ADMIN")
    @DisplayName("모든 회원 조회 테스트")
    public void 모든_회원_조회_테스트() throws Exception{
        // given
        when(adminService.getMembers()).thenReturn(response);

        // when, then
        MvcResult result = mockMvc.perform(get(AdminController.ADMIN_API_URI + "/members"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    @DisplayName("모든 관리자 조회 테스트")
    public void 모든_관리자_조회_테스트() throws Exception{
        when(adminService.getAdmins()).thenReturn(response);

        // when, then
        MvcResult result = mockMvc.perform(get(AdminController.ADMIN_API_URI + "/admins"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
