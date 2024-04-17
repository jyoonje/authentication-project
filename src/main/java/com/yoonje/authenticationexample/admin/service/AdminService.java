package com.yoonje.authenticationexample.admin.service;

import com.yoonje.authenticationexample.member.dto.member.response.MemberInfoResponse;

import java.util.List;

public interface AdminService {
    List<MemberInfoResponse> getMembers();

    List<MemberInfoResponse> getAdmins();
}
