package com.give928.spring.core;

import com.give928.spring.core.discount.DiscountPolicy;
import com.give928.spring.core.discount.RateDiscountPolicy;
import com.give928.spring.core.member.MemberRepository;
import com.give928.spring.core.member.MemberService;
import com.give928.spring.core.member.MemberServiceImpl;
import com.give928.spring.core.member.MemoryMemberRepository;
import com.give928.spring.core.order.OrderService;
import com.give928.spring.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
