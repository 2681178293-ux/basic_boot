package net.wanho.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端直传所需字段：VideoId + 两个 Base64 凭证。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VodUploadVO {

    /** 阿里云媒资 ID */
    private String videoId;

    /** CreateUploadVideo 返回的 UploadAuth（Base64） */
    private String uploadAuth;

    /** CreateUploadVideo 返回的 UploadAddress（Base64） */
    private String uploadAddress;
}
