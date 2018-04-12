package com.phil.wechat.auth.model.resp;

import com.phil.wechat.base.result.AbstractResult;

/**
 * JS-SDK的页面配置信息
 * @author phil
 * @date 2017年8月22日
 */
public class JsSDKConfig extends AbstractResult {

	private static final long serialVersionUID = 3710336862166262164L;

	private String appId;

	private long timestamp;

	private String noncestr;

	private String signature;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}