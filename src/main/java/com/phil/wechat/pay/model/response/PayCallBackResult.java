package com.phil.wechat.pay.model.response;

import com.phil.modules.annotation.NotRequire;
import lombok.Getter;
import lombok.Setter;

/**
 * 扫码模式一回调商户支付URL返回结果
 * 
 * @author phil
 * @date  2017年6月27日
 *
 */
@Getter
@Setter
public class PayCallBackResult extends AbstractPayResult {

	private static final long serialVersionUID = 8724361660920755646L;

	private String return_code; // 返回状态码SUCCESS/FAIL
	@NotRequire
	private String return_msg;// 返回信息

	private String prepay_id;// 预支付ID

	private String result_code; // 业务结果SUCCESS/FAIL
	@NotRequire
	private String err_code_des;// 错误返回的信息描述

}
