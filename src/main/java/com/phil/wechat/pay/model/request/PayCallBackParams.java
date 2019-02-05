package com.phil.wechat.pay.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 扫码模式一回调商户支付URL请求参数
 * @author phil
 * @date  2017年6月27日
 *
 */
@Getter
@Setter
@ToString
public class PayCallBackParams implements Serializable {
	
	private static final long serialVersionUID = -1996870796182913776L;
	private String appid;// 公众账号ID

	private String openid;// 用户标识

	private String mch_id;// 商户号

	private String is_subscribe;// 用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注

	private String nonce_str; // 随机字符串，不长于32位

	private String product_id; // 商户定义的商品id 或者订单号

	private String sign;// 签名
}
