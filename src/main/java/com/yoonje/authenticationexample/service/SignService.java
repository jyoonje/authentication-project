package com.yoonje.authenticationexample.service;

import com.yoonje.authenticationexample.dto.sign_in.request.SignInRequest;
import com.yoonje.authenticationexample.dto.sign_in.response.SignInResponse;
import com.yoonje.authenticationexample.dto.sign_up.request.SignUpRequest;
import com.yoonje.authenticationexample.dto.sign_up.response.SignUpResponse;
import com.yoonje.authenticationexample.entity.Member;
import com.yoonje.authenticationexample.repository.MemberRepository;
import com.yoonje.authenticationexample.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public SignUpResponse registerMember(SignUpRequest request){
        Member member = memberRepository.save(Member.from(request, encoder));
        try{
            memberRepository.flush();;
        }catch(DataIntegrityViolationException e){
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        return SignUpResponse.from(member);
    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest request){
        Member member = memberRepository.findByAccount(request.account())
                .filter(login -> encoder.matches(request.password(), login.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        String token = tokenProvider.createToken(String.format("%s:%s", member.getId(), member.getType()));
        return new SignInResponse(member.getName(), member.getType(), token);
    }

}
