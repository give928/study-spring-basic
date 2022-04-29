package com.give928.spring.core.scope;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.junit.jupiter.api.Assertions.assertSame;

@Slf4j
class SingletonTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        log.debug("singletonBean1 = {}", singletonBean1);
        log.debug("singletonBean2 = {}", singletonBean2);

        assertSame(singletonBean1, singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            log.debug("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            log.debug("SingletonBean.destroy");
        }
    }
}
