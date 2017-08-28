package com.phil.wechatpay.model.resp;

import java.io.Serializable;

import com.phil.common.annotation.NotRequire;

/**
 * 扫码模式一回调商户支付URL返回结果
 * 
 * @author phil
 * @date  2017年6月27日
 *
 */
public class PayCallBackResult extends AbstractPayResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8724361660920755646L;

	private String return_code; // 返回状态码SUCCESS/FAIL
	@NotRequire
	private String return_msg;// 返回信息

	private String prepay_id;// 预支付ID

	private String result_code; // 业务结果SUCCESS/FAIL
	@NotRequire
	private String err_code_des;// 错误返回的信息描述

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

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

}
