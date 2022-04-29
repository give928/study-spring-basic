package com.give928.spring.core.common;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class MyLogger {
    private String uuid;
    @Setter
    private String requestURL;

    public void log(String message) {
        log.info("[{}][{}] {}", uuid, requestURL, message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        log.info("[{}] request scope bean create: {}", uuid, this);
    }

    @PreDestroy
    public void close() {
        log.info("[{}] request scope bean close: {}", uuid, this);
    }
}
