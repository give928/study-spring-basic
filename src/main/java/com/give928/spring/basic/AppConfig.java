package com.give928.spring.basic;

import com.give928.spring.basic.order.OrderService;
import com.give928.spring.basic.order.OrderServiceImpl;
import com.give928.spring.basic.discount.DiscountPolicy;
import com.give928.spring.basic.discount.RateDiscountPolicy;
import com.give928.spring.basic.member.MemberRepository;
import com.give928.spring.basic.member.MemberService;
import com.give928.spring.basic.member.MemberServiceImpl;
import com.give928.spring.basic.member.MemoryMemberRepository;
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
