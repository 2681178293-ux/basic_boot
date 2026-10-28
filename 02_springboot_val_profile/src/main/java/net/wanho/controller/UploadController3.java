package net.wanho.controller;

import lombok.RequiredArgsConstructor;
import net.wanho.config.UploadProperties2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload3")
@RequiredArgsConstructor
@EnableConfigurationProperties(UploadProperties2.class)
public class UploadController3 {

    private final UploadProperties2 uploadProperties2;

    @GetMapping("/r3")
    public String r1(){
        return uploadProperties2.toString();
    }

}
