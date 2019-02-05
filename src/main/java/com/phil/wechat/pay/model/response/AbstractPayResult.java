package com.phil.wechat.pay.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回参数的抽类型
 * @author phil
 * @date  2017年6月27日
 *
 */
@Getter
@Setter
public abstract class AbstractPayResult implements Serializable {
	
	private static final long serialVersionUID = 8862987039336247667L;

	private String appid; // 公众号id

	private String mch_id; // 商户号

	private String nonce_str; // 随机字符串

	private String sign; // 签名

}