package net.wanho.controller;

import lombok.RequiredArgsConstructor;
import net.wanho.service.VodUploadServiceI;
import net.wanho.vo.VodUploadVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端直传：后端只签发凭证，视频字节不经过本机。
 * 上传/转码进度以 VOD HTTP 回调为准，不另设 complete 接口。
 */
@RestController
@RequestMapping("/api/vod")
@RequiredArgsConstructor
public class VodUploadController {

    private final VodUploadServiceI vodUploadService;

    /**
     * 获取上传凭证。浏览器拿到后直传点播关联 OSS。
     */
    @GetMapping("/upload/credential")
    public VodUploadVO credential(@RequestParam("filename") String filename,
                                  @RequestParam(value = "title", required = false) String title) throws Exception {
        return vodUploadService.createUploadCredential(filename, title);
    }
}
