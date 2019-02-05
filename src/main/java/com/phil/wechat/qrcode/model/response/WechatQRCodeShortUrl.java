package com.phil.wechat.qrcode.model.response;

import com.google.gson.annotations.SerializedName;
import com.phil.modules.result.ResultState;
import lombok.Getter;
import lombok.Setter;

/**
 * 二维码短链接返回结果
 * @author phil
 * @date  2017年7月29日
 *
 */
@Getter
@Setter
public class WechatQRCodeShortUrl extends ResultState {

    private static final long serialVersionUID = -2491236254633842526L;

    @SerializedName("short_url")
	private String shortUrl; //短链接
}