package com.yoonje.authenticationexample.admin.service;

import com.yoonje.authenticationexample.common.MemberType;
import com.yoonje.authenticationexample.member.dto.member.response.MemberInfoResponse;
import com.yoonje.authenticationexample.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralAdminService implements AdminService{
    private final MemberRepository memberRepository;
    @Override
    public List<MemberInfoResponse> getMembers() {
        return memberRepository.findAllByType(MemberType.USER)
                .stream()
                .map(MemberInfoResponse::from)
                .toList();
    }
}
