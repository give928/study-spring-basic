package com.give928.spring.core.singleton;

import com.give928.spring.core.AppConfig;
import com.give928.spring.core.member.MemberRepository;
import com.give928.spring.core.member.MemberServiceImpl;
import com.give928.spring.core.member.MemoryMemberRepository;
import com.give928.spring.core.order.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertSame;

@Slf4j
class ConfigurationSingletonTest {
    static ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void configurationTest() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemoryMemberRepository memberRepository = ac.getBean("memberRepository", MemoryMemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        log.debug("memberService -> memberRepository1 = {}", memberRepository1);
        log.debug("orderService -> memberRepository2 = {}", memberRepository2);
        log.debug("memberRepository = {}", memberRepository);

        assertSame(memberRepository, memberRepository1);
        assertSame(memberRepository1, memberRepository2);
    }

    @Test
    void configurationDeep() {
        AppConfig appConfig = ac.getBean(AppConfig.class);
        log.debug("appConfig = {}", appConfig);

        Method[] methods = appConfig.getClass().getDeclaredMethods();
        for (Method method : methods) {
            log.debug("method.getName() = {}", method.getName());
        }
    }
}
