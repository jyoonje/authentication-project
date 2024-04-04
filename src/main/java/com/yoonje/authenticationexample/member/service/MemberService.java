package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.member.dto.member.response.MemberDeleteResponse;
import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.entity.Member;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;
import java.util.UUID;

public interface MemberService {
    MemberProfileResponse memberProfile(User user);
    MemberDeleteResponse memberDelete(User user);
}
