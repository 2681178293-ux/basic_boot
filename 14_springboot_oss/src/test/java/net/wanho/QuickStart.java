package net.wanho;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.credentials.Credentials;
import com.aliyun.sdk.service.oss2.models.GetObjectRequest;
import com.aliyun.sdk.service.oss2.models.GetObjectResult;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.aliyun.sdk.service.oss2.utils.IOUtils;

import java.nio.charset.StandardCharsets;

public class QuickStart {
    public static void main(String[] args) throws Exception {

        // oss-cn-shanghai.aliyuncs.com

        String region = "cn-shanghai";
        String bucket = "full1522-bucket";
        String key = "example.txt";
        String content = "Hello OSS";
        OSSClient client = OSSClient.newBuilder()
                .credentialsProvider(
                        () -> new Credentials("LTAIxxxx", "abc123xxx")
                )
                .region(region)
                .build();
        try (client) {
            boolean uploaded = false;
            try {
                client.putObject(PutObjectRequest.newBuilder()
                        .bucket(bucket)
                        .key(key)
                        .body(BinaryData.fromString(content))
                        .build());
                uploaded = true;
                System.out.println("Object uploaded");

                String downloaded;
                try (GetObjectResult result = client.getObject(GetObjectRequest.newBuilder()
                        .bucket(bucket)
                        .key(key)
                        .build())) {
                    downloaded = new String(IOUtils.toByteArray(result.body()), StandardCharsets.UTF_8);
                }
                if (!content.equals(downloaded)) {
                    throw new IllegalStateException("Downloaded content does not match uploaded content");
                }
                System.out.println("Downloaded content: " + downloaded);
            } finally {
                //if (uploaded) {
                //    client.deleteObject(DeleteObjectRequest.newBuilder()
                //            .bucket(bucket)
                //            .key(key)
                //            .build());
                //    System.out.println("Object deleted");
                //}
            }
        }

        System.out.println("Quick start completed");
    }
}
