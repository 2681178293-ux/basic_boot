package net.wanho;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import net.wanho.util.SmsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 演示通过 SmsUtil + yml 配置发送短信（与 Sample 原始 SDK 示例并存）。
 */
@SpringBootTest
class SmsUtilTest {

    @Autowired
    private SmsUtil smsUtil;

    @Test
    void sendVerifyCode() throws Exception {
        SendSmsResponse response = smsUtil.sendVerifyCode("19715627185", "1234");
        System.out.println(new Gson().toJson(response));
    }
}
