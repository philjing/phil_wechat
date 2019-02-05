package com.phil.wechat.pay.util;

import com.phil.modules.util.SignatureUtil;
import com.phil.modules.util.XmlUtil;
import com.phil.wechat.pay.model.request.AbstractPayParams;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 支付的辅助工具类
 *
 * @author phil
 * @date 2017年6月27日
 */
public class PayHelper {

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
     * @return 随机字符串
     */
    public static String createNonceStr() {
        return DigestUtils.md5Hex(StringUtils.remove(UUID.randomUUID().toString() + UUID.randomUUID().toString(), "-"));
    }

    /**
     * 生成商户订单号
     *
     * @return
     */

    //时间戳+机器码+pid+随机数
    //时间戳+UUID
    public static String createOutTradeNo() {
        return FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(new Date()) + UUID.randomUUID().toString().hashCode();
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
    public static String createPayImageUrl(Map<String, Object> params) {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                buffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return buffer.toString();
    }

    /**
     * 支付参数
     * @param params
     * @return
     */
    public static String toPayXml(AbstractPayParams params) {
        String sign = SignatureUtil.md5(params);
        params.setSign(sign);
        return XmlUtil.toXml(params);
    }
}