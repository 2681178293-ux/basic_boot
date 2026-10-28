package net.wanho.controller;

import lombok.RequiredArgsConstructor;
import net.wanho.config.UploadProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload2")
@RequiredArgsConstructor
@EnableConfigurationProperties(UploadProperties.class)
public class UploadController2 {

    private final UploadProperties uploadProperties;

    @GetMapping("/r2")
    public String r1(){
        return uploadProperties.toString();
    }

}
