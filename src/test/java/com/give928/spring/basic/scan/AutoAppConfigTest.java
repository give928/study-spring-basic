package com.give928.spring.basic.scan;

import com.give928.spring.basic.AutoAppConfig;
import com.give928.spring.basic.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@Slf4j
class AutoAppConfigTest {
    @Test
    void basicScan() {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        // when
        MemberService memberService = ac.getBean(MemberService.class);

        // then
        assertInstanceOf(MemberService.class, memberService);
    }
}
