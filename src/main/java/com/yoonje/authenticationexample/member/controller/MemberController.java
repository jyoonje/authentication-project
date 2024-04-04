package com.yoonje.authenticationexample.member.controller;

import com.yoonje.authenticationexample.member.dto.member.response.MemberDeleteResponse;
import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.entity.Member;
import com.yoonje.authenticationexample.member.service.GeneralMemberService;
import com.yoonje.authenticationexample.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.yoonje.authenticationexample.member.controller.MemberController.MEMBER_API_URI;

@Tag(name = "로그인 시 이용가능한 API")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")   // 해당 어노테이션으로인해 AccessDeniedException 발생
@RequestMapping(MEMBER_API_URI)
public class MemberController {
    public static final String MEMBER_API_URI = "/api/members";

    private final MemberService memberService;

    @Operation(summary = "내 정보 조회")
    @GetMapping(value = "/{me}")
    public ResponseEntity<MemberProfileResponse> memberProfile(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(memberService.memberProfile(user));
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping()
    public ResponseEntity<MemberDeleteResponse> memberDelete(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(memberService.memberDelete(user));
    }
}
