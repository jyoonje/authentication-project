package com.yoonje.authenticationexample.member.controller;

import com.yoonje.authenticationexample.admin.service.AdminService;
import com.yoonje.authenticationexample.common.controller.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AdminControllerTest extends ControllerTest {


    @Test
    @WithMockUser(authorities = "ADMIN")
    @DisplayName("모든 회원 조회 테스트")
    public void 모든_회원_조회_테스트() throws Exception{
//        when(adminService.getMembers()).thenReturn()
    }
}
