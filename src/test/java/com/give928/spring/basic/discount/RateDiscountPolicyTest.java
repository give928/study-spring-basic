package com.give928.spring.basic.discount;

import com.give928.spring.basic.member.Grade;
import com.give928.spring.basic.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        // given
        Member member = Member.builder()
                .id(1L)
                .name("memberVIP")
                .grade(Grade.VIP)
                .build();

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertEquals(1000, discount);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        // given
        Member member = Member.builder()
                .id(2L)
                .name("memberBASIC")
                .grade(Grade.BASIC)
                .build();

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertEquals(0, discount);
    }
}
