package net.wanho.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunSmsProperties {

    /** AccessKey ID */
    private String accessKeyId;

    /** AccessKey Secret */
    private String accessKeySecret;

    /** 短信服务 Endpoint */
    private String endpoint = "dysmsapi.aliyuncs.com";

    /** 短信签名 */
    private String signName;

    /** 默认模板 Code */
    private String templateCode;

    /** 模板中的产品名（部分模板需要） */
    private String product = "万里大学";
}
