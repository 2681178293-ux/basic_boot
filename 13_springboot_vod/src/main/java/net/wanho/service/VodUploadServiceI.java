package net.wanho.service;

import net.wanho.vo.VodUploadVO;

public interface VodUploadServiceI {

    /**
     * 向点播申请上传凭证，供浏览器直传 OSS。
     */
    VodUploadVO createUploadCredential(String filename, String title) throws Exception;
}
