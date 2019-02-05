package com.phil.modules.util;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 支付的辅助工具类
 *
 * @author phil
 * @date 2017年6月27日
 */
public class PayUtil {

    /**
     * 取出一个指定长度大小的随机正整数
     *
     * @param length
     * @return
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = ThreadLocalRandom.current().nextDouble(); //Math.random();
        if (random < 0.1) {
            random += 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获得随机字符串
     *
     * @return
     */
    public static String createNonceStr() {
        return "";
    }

    /**
     * 生成商户订单号
     *
     * @return
     */
    public static String createOutTradeNo() {
        return DateTimeUtil.formatDate(new Date(), "yyyyMMddHHmmssSSS") + UUID.randomUUID().toString().hashCode();
    }

    /**
     * 生成时间戳
     *
     * @return
     */
    public static String createTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 生成支付二维码URL
     *
     * @param params
     * @return
     */
    public static String createPayImageUrl(TreeMap<String, Object> params) {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                buffer.append("&" + entry.getKey() + "=" + entry.getValue());
            }
        }
        return buffer.toString();
    }
}