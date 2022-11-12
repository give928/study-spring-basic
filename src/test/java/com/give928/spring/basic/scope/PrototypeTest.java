package com.give928.spring.basic.scope;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.junit.jupiter.api.Assertions.assertNotSame;

@Slf4j
class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        log.debug("prototypeBean1 = {}", prototypeBean1);
        log.debug("prototypeBean2 = {}", prototypeBean2);

        assertNotSame(prototypeBean1, prototypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            log.debug("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            log.debug("PrototypeBean.destroy");
        }
    }
}
