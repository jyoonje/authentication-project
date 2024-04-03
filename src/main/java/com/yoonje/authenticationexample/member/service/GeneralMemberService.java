package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.entity.Member;
import com.yoonje.authenticationexample.member.exception.MemberNotFoundException;
import com.yoonje.authenticationexample.member.repository.MemberRepository;
import com.yoonje.authenticationexample.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService{
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Override
    public MemberProfileResponse memberProfile(String token) {
        UUID memberId = UUID.fromString(jwtUtil.getMemberIdFromToken(token));

        Member member =  memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        return new MemberProfileResponse(member.getEmail(), member.getName(), member.getAge());

    }
}
