package net.wanho.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wanho.service.VodCallbackServiceI;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 阿里云 VOD 回调服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VodCallbackServiceImpl implements VodCallbackServiceI {


    @Override
    public void handleCallback(Map<String, Object> payload) {
        String eventType = (String) payload.get("EventType");
        String aliyunVideoId = (String) payload.get("VideoId");
        String status = (String) payload.get("Status");
        String eventTime = (String) payload.get("EventTime");

        log.info("收到阿里云VOD回调，EventType={}, VideoId={}, Status={}, EventTime={}",
                eventType, aliyunVideoId, status, eventTime);



        try {
            switch (eventType) {
                case "FileUploadComplete" -> handleUploadComplete(aliyunVideoId, payload);
                case "TranscodeComplete" -> handleTranscodeComplete(aliyunVideoId, status, payload);
                case "SnapshotComplete" -> handleSnapshotComplete(aliyunVideoId, payload);
                default -> log.info("未处理的事件类型: {}", eventType);
            }
        } catch (Exception e) {
            log.error("处理VOD回调失败，EventType={}, VideoId={}", eventType, aliyunVideoId, e);
        }
    }

    private void handleUploadComplete(String aliyunVideoId, Map<String, Object> payload) {
        System.out.println("上传完成");
    }

    private void handleTranscodeComplete(String aliyunVideoId, String status, Map<String, Object> payload) {
        System.out.println("转码完成");
    }

    private void handleSnapshotComplete(String aliyunVideoId, Map<String, Object> payload) {
        System.out.println("截图完成");
    }


}
