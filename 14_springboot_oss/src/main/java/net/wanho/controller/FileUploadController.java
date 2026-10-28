package net.wanho.controller;

import net.wanho.vo.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @PostMapping("/uploae/local")
    public AjaxResult upload(@RequestParam("photo") MultipartFile file) throws IOException {
        file.transferTo(new File("d:/a.png"));
        AjaxResult success = AjaxResult.success();
        success.put("name",file.getName());
        success.put("size",file.getSize());
        return success;
    }
}
