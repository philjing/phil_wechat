package com.phil.wechat.qrcode.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * 微信带参二维码
 *
 * @author phil
 * @date 2017年6月7日
 */
@Getter
@Setter
public class WechatQRCode {
    // 获取的二维码
    private String ticket;
    // 二维码的有效时间,单位为秒,最大不超过2592000（即30天）

    @SerializedName("expire_seconds")
    private int expireSeconds;

    // 二维码图片解析后的地址
    //由于测试无法访问这个，故直接通过ticket 显示并转换成短链接
    private String url;
}