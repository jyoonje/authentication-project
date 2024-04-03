package com.yoonje.authenticationexample.member.service;

import com.yoonje.authenticationexample.member.exception.MemberNotFoundException;
import com.yoonje.authenticationexample.member.dto.sign_in.request.SignInRequest;
import com.yoonje.authenticationexample.member.dto.sign_in.response.SignInResponse;
import com.yoonje.authenticationexample.member.dto.sign_up.request.SignUpRequest;
import com.yoonje.authenticationexample.member.entity.Member;
import com.yoonje.authenticationexample.member.repository.MemberRepository;
import com.yoonje.authenticationexample.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GeneralSignService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public void signUp(SignUpRequest request){
        Member member = memberRepository.save(Member.from(request, encoder));
        try{
            memberRepository.flush();;
        }catch(DataIntegrityViolationException e){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }
    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest request){
        Member member = findMemberByEmail(request);

        String token = tokenProvider.createToken(String.format("%s:%s", member.getId(), member.getType()));
        return new SignInResponse(member.getName(), member.getType(), token);
    }


    public Member findMemberByEmail(SignInRequest request){
        return memberRepository.findByEmail(request.email())
                .filter(login -> encoder.matches(request.password(), login.getPassword()))
                .orElseThrow(MemberNotFoundException::new);
    }


}
