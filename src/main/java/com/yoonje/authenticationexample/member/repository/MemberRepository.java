package com.yoonje.authenticationexample.member.repository;

import com.yoonje.authenticationexample.common.MemberType;
import com.yoonje.authenticationexample.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmail(String email);
}