package com.phil.wechat.pay.model.response;

import com.phil.modules.annotation.NotRequire;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 统一下单返回结果
 * @author phil
 * @data 2017年6月27日
 *
 */
@Getter
@Setter
@ToString
public class UnifiedOrderResult extends AbstractPayResult {

	private static final long serialVersionUID = 9030465964635155064L;

	private String return_code; // 返回状态码

	private String return_msg; // 返回信息

	// 以下字段在return_code为SUCCESS的时候有返回(包括父类)
	private String device_info; // 设备号
	private String result_code; // 业务结果 SUCCESS/FAIL
	@NotRequire
	private String err_code; // 错误代码
	@NotRequire
	private String err_code_des; // 错误代码描述

	// 以下字段在return_code 和result_code都为SUCCESS的时候有返回
	private String trade_type; // 交易类型
	private String prepay_id; // 预支付交易会话标识，有效期为2小时
	@NotRequire
	private String code_url; // 二维码链接

}