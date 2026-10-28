package net.wanho.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "app.upload")
public class UploadProperties {

    private String maxSize;
    private List<String> allowedTypes;
    private String storagePath;
}
