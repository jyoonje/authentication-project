//package com.yoonje.authenticationexample.admin;
//
//import com.yoonje.authenticationexample.common.MemberType;
//import com.yoonje.authenticationexample.member.entity.Member;
//import com.yoonje.authenticationexample.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class AdminInitializer implements ApplicationRunner {
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder encoder;
//
//
//    @Override
//    public void run(ApplicationArguments args){
//        memberRepository.save(Member.builder()
//                .email("admin@admin.com")
//                .password(encoder.encode("admin"))
//                .name("관리자")
//                .type(MemberType.ADMIN)
//                .build());
//    }
//}
