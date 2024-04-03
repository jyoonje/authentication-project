package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.entity.Member;

import java.util.Optional;

public interface MemberService {
    MemberProfileResponse memberProfile(String memberId);
}
