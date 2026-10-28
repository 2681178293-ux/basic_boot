package net.wanho;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailTest {

    @Resource
    private JavaMailSender mailSender;

    @Test
    public void testSendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("89115008@qq.com");
        message.setTo("89115008@qq.com");
        message.setSubject("主题测试");
        message.setText("内容测试");
        mailSender.send(message);
    }
}
