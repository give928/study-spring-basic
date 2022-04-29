package com.give928.spring.core.xml;

import com.give928.spring.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class XmlApplicationContextTest {
    @Test
    void xmlApplicationContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertInstanceOf(MemberService.class, memberService);
    }
}
