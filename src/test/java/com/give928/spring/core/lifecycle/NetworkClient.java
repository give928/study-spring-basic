package com.give928.spring.core.lifecycle;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class NetworkClient {
    private String url;

    public NetworkClient() {
        log.debug("생성자 호출, url = {}", url);
    }

    public void setUrl(String url) {
        log.debug("setUrl url = " + url);
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        log.debug("connect url = {}", url);
    }

    public void call(String message) {
        log.debug("call url = {} message = {}", url, message);
    }

    public void disconnect() {
        log.debug("disconnect url = {}", url);
    }

    @PostConstruct
    public void init() {
        log.debug("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        log.debug("NetworkClient.close");
        disconnect();
    }
}
