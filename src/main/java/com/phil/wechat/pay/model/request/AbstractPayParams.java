package com.phil.wechat.pay.model.request;

import com.phil.modules.annotation.NotRequire;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 请求参数的基类
 * @author phil
 * @date  2017年6月27日
 *
 */
@Getter
@Setter
@ToString
public abstract class AbstractPayParams implements Serializable{

	private static final long serialVersionUID = 8271906995431476063L;

	private String appid; // 公众号id

	private String mch_id; // 商户号
	private String nonce_str; // 随机字符串
	private String sign; // 签名
	@NotRequire
	private String sign_type; // 签名类型

}