package net.wanho.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class HelloService {

    public String hello() {
        log.info("同步处理中");
        return "hello";
    }

    @Async
    public CompletableFuture<String> helloAsync() {
        log.info("异步处理中（应仍带同一 traceId）");
        return CompletableFuture.completedFuture("async-ok");
    }
}
