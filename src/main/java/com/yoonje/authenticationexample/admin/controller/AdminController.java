package com.yoonje.authenticationexample.admin.controller;

import com.yoonje.authenticationexample.admin.service.AdminService;
import com.yoonje.authenticationexample.member.dto.member.response.MemberInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.yoonje.authenticationexample.admin.controller.AdminController.ADMIN_API_URI;

@Tag(name = "관리자용 API")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(ADMIN_API_URI)
public class AdminController {
    public static final String ADMIN_API_URI = "/api/admin";
    private final AdminService adminService;

    @Operation(summary = "모든 회원 조회")
    @GetMapping("/members")
    public ResponseEntity<List<MemberInfoResponse>> allMembers(){
        return ResponseEntity.ok(adminService.getMembers());
    }

    @Operation(summary = "모든 관리자 조회")
    @GetMapping("/admins")
    public ResponseEntity<List<MemberInfoResponse>> allAdmins(){
        return ResponseEntity.ok(adminService.getAdmins());
    }
}
