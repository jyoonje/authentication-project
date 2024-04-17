package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.common.util.UuidConverter;
import com.yoonje.authenticationexample.member.dto.member.request.MemberUpdateRequest;
import com.yoonje.authenticationexample.member.dto.member.response.MemberDeleteResponse;
import com.yoonje.authenticationexample.member.dto.member.response.MemberProfileResponse;
import com.yoonje.authenticationexample.member.dto.member.response.MemberUpdateResponse;
import com.yoonje.authenticationexample.member.entity.Member;
import com.yoonje.authenticationexample.member.exception.MemberNotFoundException;
import com.yoonje.authenticationexample.member.repository.MemberRepository;
//import com.yoonje.authenticationexample.security.JwtUtil;
import com.yoonje.authenticationexample.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    @Override
    public MemberProfileResponse memberProfile(User user) {
        UUID id = UuidConverter.fromUser(user);

        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return new MemberProfileResponse(member.getEmail(), member.getName(), member.getAge());
    }

    @Override
    public MemberDeleteResponse memberDelete(User user) {
        UUID id = UuidConverter.fromUser(user);

        if(!memberRepository.existsById(id)) return new MemberDeleteResponse(false);

        memberRepository.deleteById(id);
        return new MemberDeleteResponse(true);
    }

    @Override
    public MemberUpdateResponse memberUpdate(User user, MemberUpdateRequest request) {
        UUID id = UuidConverter.fromUser(user);

        return memberRepository.findById(id)
                .filter(member -> encoder.matches(request.password(), member.getPassword()))
                .map(member -> {
                    member.update(request, encoder);
                    return MemberUpdateResponse.of(true, member);
                })
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }
}
