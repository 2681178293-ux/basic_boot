package net.wanho.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app.upload")
public record UploadProperties2 (
   String maxSize,
   List<String> allowedTypes,
   String storagePath
){}
