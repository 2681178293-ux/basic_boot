package net.wanho.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotifyService {

    @Async
    public void sendEmail(String email, String content) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送邮件：" + email + "，内容：" + content+"-"+Thread.currentThread().getName());
    }
}
