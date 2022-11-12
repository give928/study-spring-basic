package com.give928.spring.basic.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
