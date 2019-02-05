package com.phil.wechat.pay.model.response;

import com.phil.modules.annotation.NotRequire;
import lombok.Getter;
import lombok.Setter;

/**
 * 二维码链接转成短链接返回结果
 * @author phil
 * @date  2017年8月1日
 *
 */
@Getter
@Setter
public class PayShortUrlResult extends AbstractPayResult {

	private static final long serialVersionUID = -1314336922987305936L;

	private String return_code; // 返回状态码SUCCESS/FAIL
	
	@NotRequire
	private String return_msg;// 返回信息
	/**** 以下字段在return_code为SUCCESS的时候有返回 ****/
	private String result_code; // 业务结果 SUCCESS/FAIL
	@NotRequire
	private String err_code;// 错误返回的信息描述
	
	private String short_url;
}
