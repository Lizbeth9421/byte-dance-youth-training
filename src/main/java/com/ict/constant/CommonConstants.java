package com.ict.constant;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/18/20:22
 */
public class CommonConstants {
    /**
     * jwt中的信息
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";


    /**
     * 用户昵称前缀
     */
    public static final String USER_PREFIX = "user_";


    /**
     * 基本上传组路径
     */
    public static final String BASE_UPLOAD_GROUP = "dousheng/";

    /**
     * 视频上传组路径
     */
    public static final String VIDEO_UPLOAD_GROUP = "video/";

    /**
     * 图片上传组路径
     */
    public static final String IMAGE_UPLOAD_GROUP = "image/";


    /**
     * 阿里云的视频截图参数
     */
    public static final String ALIYUN_OSS_IMAGE_PARAMS = "?x-oss-process=video/snapshot,t_1000,m_fast";

}
