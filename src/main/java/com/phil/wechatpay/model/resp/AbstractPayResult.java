package com.phil.wechatpay.model.resp;

/**
 * 返回参数的抽类型
 * @author phil
 * @date  2017年6月27日
 *
 */
public abstract class AbstractPayResult {
	
	private String appid; // 公众号id

	private String mch_id; // 商户号

	private String nonce_str; // 随机字符串

	private String sign; // 签名

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}