package com.phil.wechat.qrcode.model;

import com.phil.wechat.base.result.ResultState;

/**
 * 二维码短链接返回结果
 * @author phil
 * @date  2017年7月29日
 *
 */
public class WechatQRCodeShortUrl extends ResultState{
	
	private String short_url; //短链接

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}
}