package com.give928.spring.basic.order;

import com.give928.spring.basic.AppConfig;
import com.give928.spring.basic.member.Grade;
import com.give928.spring.basic.member.Member;
import com.give928.spring.basic.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = Member.builder()
                .id(memberId)
                .name("memberA")
                .grade(Grade.VIP)
                .build();
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        assertEquals(1000, order.getDiscountPrice());
    }
}
