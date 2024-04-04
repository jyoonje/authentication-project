package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.entity.Member;

import java.util.Optional;
import java.util.UUID;

public interface MemberService {
    MemberProfileResponse memberProfile(UUID id);
}
