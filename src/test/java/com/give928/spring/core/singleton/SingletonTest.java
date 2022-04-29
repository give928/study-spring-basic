package com.give928.spring.core.singleton;

import com.give928.spring.core.AppConfig;
import com.give928.spring.core.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@Slf4j
class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        log.debug("memberService1 = {}", memberService1);
        log.debug("memberService2 = {}", memberService2);

        // memberService1 != memberService2
        assertNotSame(memberService1, memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonPatternTest() {
        Singleton object1 = Singleton.getInstance();
        Singleton object2 = Singleton.getInstance();

        log.debug("object1 = {}", object1);
        log.debug("object2 = {}", object2);

        assertSame(object1, object2);
    }

    static class Singleton {
        private static final Singleton instance = new Singleton();

        private Singleton() {
        }

        public static Singleton getInstance() {
            return instance;
        }
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른 것을 확인
        log.debug("memberService1 = {}", memberService1);
        log.debug("memberService2 = {}", memberService2);

        // memberService1 == memberService2
        assertSame(memberService1, memberService2);
    }
}
