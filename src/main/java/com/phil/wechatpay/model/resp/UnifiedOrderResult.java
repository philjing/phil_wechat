package com.phil.wechatpay.model.resp;

import java.io.Serializable;

import com.phil.common.annotation.NotRequire;

/**
 * 统一下单返回结果
 * @author phil
 * @data 2017年6月27日
 *
 */
public class UnifiedOrderResult extends AbstractPayResult implements Serializable {

	/**
	 * 
	 */
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

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	@Override
	public String toString() {
		return "UnifiedOrderResult [return_code=" + return_code + ", return_msg=" + return_msg + ", device_info="
				+ device_info + ", result_code=" + result_code + ", err_code=" + err_code + ", err_code_des="
				+ err_code_des + ", trade_type=" + trade_type + ", prepay_id=" + prepay_id + ", code_url=" + code_url
				+ "]";
	}

}