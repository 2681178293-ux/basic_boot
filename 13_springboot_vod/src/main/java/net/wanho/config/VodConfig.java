package net.wanho.config;

import com.aliyun.teaopenapi.models.Config;
import com.aliyun.vod20170321.Client;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(VodProperties.class)
public class VodConfig {

    @Bean
    public Client client(VodProperties vodProperties) throws Exception {
        Config config = new Config()
                .setAccessKeyId(vodProperties.getAccessKeyId())
                .setAccessKeySecret(vodProperties.getAccessKeySecret())
                .setRegionId(vodProperties.getRegionId())
                .setEndpoint("vod." + vodProperties.getRegionId() + ".aliyuncs.com");
        return new Client(config);
    }
}
