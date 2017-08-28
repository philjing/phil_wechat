package com.phil.wechatpay.model.rep;

import java.io.Serializable;

/**
 * 微信内H5调起支付参数
 * @author phil
 * @date  2017年6月27日
 *
 */
public class JsPayParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8255883197124904824L;

	private String appId; // 公众号id

	private String timeStamp; // 时间戳 格式1414561699

	private String nonceStr; // 随机字符串

	private String packageStr; // package参数 订单详情扩展字符串 prepay_id=***

	private String signType = "MD5"; // 签名方式

	private String paySign; // 签名

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

}
