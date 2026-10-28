package net.wanho;

import com.aliyun.vod20170321.Client;
import com.aliyun.vod20170321.models.GetVideoInfoRequest;
import com.aliyun.vod20170321.models.GetVideoInfoResponse;
import com.aliyun.vod20170321.models.GetVideoInfoResponseBody;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest {

    @Resource
    private Client client;

    @Test
    public void test() throws Exception {
        GetVideoInfoRequest req = new GetVideoInfoRequest().setVideoId("20b4c461947d71f180535017f0e90102");
        GetVideoInfoResponse resp = client.getVideoInfo(req);
        GetVideoInfoResponseBody.GetVideoInfoResponseBodyVideo video = resp.getBody().getVideo();
        System.out.println(video.getTitle());
        System.out.println(video.getStatus());
        System.out.println(video.getDuration());
        System.out.println(video.getCoverURL());
        System.out.println(video.getCateId());
        System.out.println(video.getSize());
        System.out.println(video.getSnapshots().snapshot);
    }
}
