package net.wanho.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wanho.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping("/api/hello")
    public String hello() {
        log.info("进入 Controller");
        return helloService.hello();
    }

    @GetMapping("/api/async")
    public CompletableFuture<String> async() {
        log.info("准备提交异步任务");
        return helloService.helloAsync();
    }
}
