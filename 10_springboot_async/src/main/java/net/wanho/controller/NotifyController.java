package net.wanho.controller;

import lombok.RequiredArgsConstructor;
import net.wanho.service.NotifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotifyController {

    private final NotifyService notifyService;

    @GetMapping("/send")
    public String send(){
        notifyService.sendEmail("aa@aa.com","好好学习天天向上");
        System.out.println("controller send");
        return "发送成功";
    }
}
