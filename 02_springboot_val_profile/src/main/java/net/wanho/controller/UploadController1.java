package net.wanho.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload1")
public class UploadController1 {

    @Value("${app.upload.max-size}")
    private String maxSize;
    @Value("${app.upload.storage-path}")
    private String storagePath;

    @GetMapping("/r1")
    public String r1(){
        return maxSize+"-"+storagePath;
    }


}
