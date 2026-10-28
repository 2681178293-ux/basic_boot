package net.wanho.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.vod20170321.Client;
import com.aliyun.vod20170321.models.CreateUploadVideoRequest;
import com.aliyun.vod20170321.models.CreateUploadVideoResponse;
import com.aliyun.vod20170321.models.CreateUploadVideoResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wanho.config.VodProperties;
import net.wanho.service.VodUploadServiceI;
import net.wanho.vo.VodUploadVO;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VodUploadServiceImpl implements VodUploadServiceI {

    private final Client client;
    private final VodProperties vodProperties;

    @Override
    public VodUploadVO createUploadCredential(String filename, String title) throws Exception {
        if (StrUtil.isBlank(filename)) {
            throw new IllegalArgumentException("filename 不能为空");
        }
        String videoTitle = StrUtil.isNotBlank(title) ? title.trim() : filename.trim();

        CreateUploadVideoRequest request = new CreateUploadVideoRequest()
                .setTitle(videoTitle)
                // 只传文件名即可，点播用扩展名分配对象；不要传本地绝对路径
                .setFileName(filename.trim())
                .setCateId(vodProperties.getCateId())
                .setWorkflowId(vodProperties.getWorkflowId());

        CreateUploadVideoResponse response = client.createUploadVideo(request);
        CreateUploadVideoResponseBody body = response.getBody();
        if (body == null || StrUtil.isBlank(body.getVideoId())) {
            throw new IllegalStateException("CreateUploadVideo 返回为空");
        }

        log.info("签发上传凭证 VideoId={}, title={}", body.getVideoId(), videoTitle);
        return new VodUploadVO(body.getVideoId(), body.getUploadAuth(), body.getUploadAddress());
    }
}
