package com.penoder.dailynews.config;

import com.penoder.mylibrary.utils.EncryptUtil;

/**
 * @author Penoder
 * @date 18-4-18.
 */
public class NewsApi {

    /*
     * API 测试站点 https://fenfa.shuwen.com/tools?spm=fenfa.0.0.1.agG24M
     */

    /**
     * Access Key 用于标识访问者身份;
     */
    public static final String ACCESS_KEY = "YyTalvkfXh3wbn2L";

    /**
     * 用于加密签名字符串和服务器端验证签名字符串的密钥;
     */
    public static final String SECRET_KEY = "8592e0bea35340a4a7981d05bb15b440";

    /**
     * 生成签名：按照Secret Key、timestamp、Access Key 的顺序，将以上三个参数的值顺序链接，并取MD5值
     */
    public static String getSignature() {
        return getSignature(System.currentTimeMillis());
    }

    private static String getSignature(long unixTime) {
        return EncryptUtil.strToMD5(SECRET_KEY + System.currentTimeMillis() + ACCESS_KEY);
    }

    public static String getNewsCategory() {
        return "https://api.xinwen.cn/news/category?signature=" + getSignature()
                + "&access_key=" + ACCESS_KEY + "&timestamp=" + System.currentTimeMillis();
    }

}
