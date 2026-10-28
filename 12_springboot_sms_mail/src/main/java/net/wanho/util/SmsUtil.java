package net.wanho.util;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wanho.config.AliyunSmsProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信发送工具类，凭据与签名/模板从 yml 读取。
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SmsUtil {

    private final AliyunSmsProperties properties;

    /**
     * 使用默认签名与模板发送验证码短信。
     *
     * @param phoneNumbers 手机号
     * @param code         验证码
     */
    public SendSmsResponse sendVerifyCode(String phoneNumbers, String code) throws Exception {
        String templateParam = String.format(
                "{\"code\":\"%s\",\"product\":\"%s\"}",
                code,
                properties.getProduct()
        );
        return send(phoneNumbers, properties.getTemplateCode(), templateParam);
    }

    /**
     * 使用默认签名、指定模板与模板参数发送短信。
     */
    public SendSmsResponse send(String phoneNumbers, String templateCode, String templateParam) throws Exception {
        return send(phoneNumbers, properties.getSignName(), templateCode, templateParam);
    }

    /**
     * 完整参数发送短信。
     */
    public SendSmsResponse send(String phoneNumbers, String signName, String templateCode, String templateParam)
            throws Exception {
        Client client = createClient();
        SendSmsRequest request = new SendSmsRequest()
                .setPhoneNumbers(phoneNumbers)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam(templateParam);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            SendSmsResponse response = client.sendSmsWithOptions(request, runtime);
            log.info("短信发送成功 phone={}, code={}", phoneNumbers,
                    response.getBody() != null ? response.getBody().getCode() : null);
            return response;
        } catch (TeaException error) {
            log.error("短信发送失败 phone={}, msg={}, recommend={}",
                    phoneNumbers, error.getMessage(),
                    error.getData() != null ? error.getData().get("Recommend") : null);
            throw error;
        }
    }

    private Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(properties.getAccessKeyId())
                .setAccessKeySecret(properties.getAccessKeySecret());
        config.endpoint = properties.getEndpoint();
        return new Client(config);
    }
}
