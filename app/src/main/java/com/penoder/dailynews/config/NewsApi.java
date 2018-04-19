package com.penoder.dailynews.config;

import android.text.TextUtils;

import com.penoder.mylibrary.utils.EncryptUtil;

/**
 * @author Penoder
 * @date 18-4-18.
 */
public class NewsApi {

    /**
     * Access Key 用于标识访问者身份;
     */
    private static final String ACCESS_KEY = "YyTalvkfXh3wbn2L";

    /**
     * 用于加密签名字符串和服务器端验证签名字符串的密钥;
     */
    private static final String SECRET_KEY = "8592e0bea35340a4a7981d05bb15b440";

    /**
     * 生成签名：按照Secret Key、timestamp、Access Key 的顺序，将以上三个参数的值顺序链接，并取MD5值
     */
    private static String getSignature(long unixTime) {
        return EncryptUtil.strToMD5(SECRET_KEY + unixTime + ACCESS_KEY);
    }

    /**
     * 获取公共参数
     */
    private static String getPublicParams() {
//        signature=0308ddc5d01eb1ee2e3688610ae57f27&timestamp=1524104389423&access_key=YyTalvkfXh3wbn2L
        long unixTime = System.currentTimeMillis();
        return getSignature(unixTime) + "&timestamp=" + unixTime + "&access_key=" + ACCESS_KEY;
    }

    /**
     * 获取新闻分类
     */
    public static String getNewsCategory() {
        return "https://api.xinwen.cn/news/category?" + getPublicParams();
    }

    /**
     * 获取新闻
     *
     * @param category 新闻种类（String 否）
     * @param region   地域过滤条件（String 否）
     * @param first_id 上一次调用的第一个ID。本次返回仅会给出比此ID更新的新闻。
     *                 若不设置，从第一条数据开始获取。当同时指定first_id及last_id时会抛出异常（String 否）
     * @param last_id  上一次API调用的最后一个ID。本次返回仅会给出此ID后面的新闻。
     *                 若不设置，从第一条数据开始获取。（String 否）
     * @param size     单次调用返回的新闻数量。取值 0-20，默认15。（int 否）
     */
    public static String getNewsAll(String category, String region, String first_id, String last_id, int size) {
        String newsUrl = "https://api.xinwen.cn/news/all?" + getPublicParams();
        if (!TextUtils.isEmpty(category)) {
            newsUrl = newsUrl + "&category=" + category;
        }
        if (!TextUtils.isEmpty(region)) {
            newsUrl = newsUrl + "&region=" + region;
        }
        if (!TextUtils.isEmpty(first_id)) {
            newsUrl = newsUrl + "&first_id=" + first_id;
        }
        if (!TextUtils.isEmpty(last_id)) {
            newsUrl = newsUrl + "&last_id=" + last_id;
        }
        if (size < 0 || size > 20) {
            size = 15;
        }
        return newsUrl + "&size=" + size;
    }

    /**
     * 新闻搜索
     *
     * @param category 新闻种类（String 否）
     * @param keyWord  搜索的关键字（String 是）
     * @param last_id  上一次API调用的最后一个ID。本次返回仅会给出此ID后面的新闻。
     *                 若不设置，从第一条数据开始获取。（String 否）
     * @param order    输出结果的排序方法。取值为 time 或 relevance，即按照时间降序排列
     *                 或者按照相关度降序排列。默认按照相关度排序。（String 否）
     * @param region   地域过滤条件（String 否）
     * @param size     单次调用返回的新闻数量。取值 0-20，默认15。（int 否）
     */
    public static String getNewsSearch(String category, String keyWord, String last_id, String order, String region, int size) {
        String newsUrl = "https://api.xinwen.cn/news/search?" + getPublicParams();
        if (TextUtils.isEmpty(keyWord)) {
            return null;
        } else {
            newsUrl = newsUrl + "&q=" + keyWord;
        }
        if (!TextUtils.isEmpty(category)) {
            newsUrl = newsUrl + "&category=" + category;
        }
        if (!TextUtils.isEmpty(last_id)) {
            newsUrl = newsUrl + "&last_id=" + last_id;
        }
        if (!TextUtils.isEmpty(order)) {
            newsUrl = newsUrl + "&order=" + order;
        }
        if (!TextUtils.isEmpty(region)) {
            newsUrl = newsUrl + "&region=" + region;
        }
        if (size < 0 || size > 20) {
            size = 15;
        }
        return newsUrl + "&size=" + size;
    }

}
