package com.give928.spring.basic.beanfind;

import com.give928.spring.basic.discount.DiscountPolicy;
import com.give928.spring.basic.discount.FixDiscountPolicy;
import com.give928.spring.basic.discount.RateDiscountPolicy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ApplicationContextExtendsBeansTest {
    static AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 종복 오류가 발생한다.")
    void findDuplicationBeanByParentType() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByNameAndParentType() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertInstanceOf(RateDiscountPolicy.class, rateDiscountPolicy);
    }

    @Test
    @DisplayName("하위 타입으로 조회")
    void findBeanBySubType() {
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        assertInstanceOf(RateDiscountPolicy.class, rateDiscountPolicy);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findBeansByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertEquals(2, beansOfType.size());

        for (String key : beansOfType.keySet()) {
            log.debug("key = {} value = {}", key, beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("Object 타입으로 모두 조회")
    void findAllBeansByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
//        assertEquals(2, beansOfType.size());

        for (String key : beansOfType.keySet()) {
            log.debug("key = {} value = {}", key, beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
