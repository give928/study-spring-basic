package com.give928.spring.core.beanfind;

import com.give928.spring.core.AppConfig;
import com.give928.spring.core.member.MemberService;
import com.give928.spring.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicBeansTest {
    static AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByNameAndType() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertInstanceOf(MemberService.class, memberService);
    }

    @Test
    @DisplayName("이름없이 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertInstanceOf(MemberService.class, memberService);
    }

    @Test
    @DisplayName("이름과 구체 타입으로 조회")
    void findBeanByNameAndImplType() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertInstanceOf(MemberService.class, memberService);
    }

    @Test
    @DisplayName("없는 이름의 빈 조회 불가")
    void notFindBeanByNameAndType() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("userService", MemberServiceImpl.class));
    }
}
