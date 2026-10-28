package net.wanho;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.PresignOptions;
import com.aliyun.sdk.service.oss2.models.*;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import jakarta.annotation.Resource;
import net.wanho.config.OssProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;

@SpringBootTest
class ApplicationTests {

    @Resource
    private  OSSClient ossClient;
    @Resource
    private OssProperties ossProperties;

    //创建桶
    @Test
    void createBucket() {

        boolean b = ossClient.doesBucketExist("my-bucket-152");

        if(!b) {
            ossClient.putBucket(PutBucketRequest.newBuilder()
                    .bucket("my-bucket-152")
                    .build());
        }
    }

    //删除桶
    @Test
    void deleteBucket(){
        ossClient.deleteBucket(DeleteBucketRequest.newBuilder().bucket("my-bucket-152").build());
    }

    //列出桶中文件的对象
    @Test
    void listObjects(){
        ListObjectsV2Result result = ossClient.listObjectsV2(ListObjectsV2Request.newBuilder()
                .bucket(ossProperties.getBucket())
//                .prefix("08/08/")
                .build());
        result.contents().forEach(o->System.out.println(o.key()));
    }

    //将照片放入新创建的文件夹再放入桶中
    @Test
    void upload() throws Exception {
        File file = new File("D://1.jpg");
        InputStream in = new FileInputStream(file);

        PutObjectRequest request = PutObjectRequest.newBuilder()
                .bucket(ossProperties.getBucket())
                .key("08/08/b.jpg")
                .body(BinaryData.fromStream(in))
                .contentType("image/jpeg")
                .build();
        ossClient.putObject(request);
        in.close();
    }

    //限时
    @Test
    void getImageUrlTimeOut(){
        GetObjectRequest request = GetObjectRequest.newBuilder()
                .bucket(ossProperties.getBucket())
                .key("08/08/b.jpg")
                .build();
        PresignOptions options = PresignOptions.newBuilder()
                .expiration(Duration.ofHours(1))
                .build();
        PresignResult presign = ossClient.presign(request, options);
        System.out.println(presign.url());
    }

    //永久
    @Test
    void getImageUrl(){
        String key="08/08/a.jpg";
        String url ="https://"+ossProperties.getBucket()+".oss-"+ossProperties.getRegion()+".aliyuncs.com/"+key;
        System.out.println(url);
    }

    //删除照片
    @Test
    void deleteImage(){
        DeleteObjectRequest req = DeleteObjectRequest.newBuilder()
                .bucket(ossProperties.getBucket())
                .key("08/08/b.jpg")
                .build();
        ossClient.deleteObject(req);
    }


}
