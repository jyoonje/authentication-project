package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.entity.Member;
import com.yoonje.authenticationexample.member.exception.MemberNotFoundException;
import com.yoonje.authenticationexample.member.repository.MemberRepository;
//import com.yoonje.authenticationexample.security.JwtUtil;
import com.yoonje.authenticationexample.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService{
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Override
    public MemberProfileResponse memberProfile(UUID id) {

//        String memberSpecification = tokenProvider.validateTokenAndGetSubject(token);

//        String[] parts = memberSpecification.split(":");
//        if(parts.length<2) throw new IllegalArgumentException("잘못된 토큰입니다.");
//
//        UUID memberId = UUID.fromString(parts[0]);
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(MemberNotFoundException::new);


        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return new MemberProfileResponse(member.getEmail(), member.getName(), member.getAge());
    }
}
