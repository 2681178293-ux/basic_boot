package net.wanho.config;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.credentials.Credentials;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssConfig {

    @Bean
    public OSSClient ossClient(OssProperties ossProperties){
        OSSClient client = OSSClient.newBuilder()
                .credentialsProvider(
                        () -> new Credentials(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret())
                )
                .region(ossProperties.getRegion())
                .build();
        return client;
    }
}
