package com.phil.wechat.pay.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 微信内H5调起支付参数
 * @author phil
 * @date  2017年6月27日
 *
 */
@Getter
@Setter
@ToString
public class JsPayParams implements Serializable{

	private static final long serialVersionUID = 8255883197124904824L;

	private String appId; // 公众号id

	private String timeStamp; // 时间戳 格式1414561699

	private String nonceStr; // 随机字符串

	private String packageStr; // package参数 订单详情扩展字符串 prepay_id=***

	private String signType = "MD5"; // 签名方式

	private String paySign; // 签名
}
