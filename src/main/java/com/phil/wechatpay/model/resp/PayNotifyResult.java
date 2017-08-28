package com.phil.wechatpay.model.resp;

import java.io.Serializable;

import com.phil.common.annotation.NotRequire;

/**
 * 统一下单API中提交的参数notify_url返回的结果
 * 
 * @author phil
 * @date 2017年6月27日
 *
 */
public class PayNotifyResult extends AbstractPayResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3616967514427448319L;
	
	@NotRequire
	private String sign_type; // 签名类型

	private String return_code; // 返回状态码SUCCESS/FAIL
	@NotRequire
	private String return_msg;// 返回信息

	/**** 以下字段在return_code为SUCCESS的时候有返回 ****/

	private String result_code; // 业务结果 SUCCESS/FAIL
	@NotRequire
	private String err_code;// 错误返回的信息描述
	@NotRequire
	private String err_code_des;// 错误返回的信息描述

	private String openid;// 用户标识
	@NotRequire
	private String is_subscribe;// Y-关注，N-未关注，仅在公众账号类型支付有效

	private String trade_type; // 交易类型 JSAPI、NATIVE、APP
	private String bank_type; // 付款银行

	private Integer total_fee; // 订单总金额，单位为分
	@NotRequire
	private Integer settlement_total_fee;// 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额
	@NotRequire
	private String fee_type;// 货币类型 默认人民币：CNY

	private Integer cash_fee; // 现金支付金额订单现金支付金额
	@NotRequire
	private String cash_fee_type; // 货币类型默认人民币：CNY
	@NotRequire
	private Integer coupon_count; // 代金券使用数量
	@NotRequire
	private String coupon_type_$n; // CASH--充值代金券 NO_CASH---非充值代金券  //待改进
	@NotRequire
	private String coupon_id_$n;// 代金券ID,$n为下标，从0开始编号  //待改进

	@NotRequire
	private Integer coupon_fee_$n; // 单个代金券支付金额   //待改进

	private String transaction_id;// 微信支付订单号
	private String out_trade_no;// 商户订单号

	@NotRequire
	private String attach; // 商家数据包

	private String time_end;// 支付完成时间 格式为yyyyMMddHHmmss

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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(Integer settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public Integer getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getCoupon_type_$n() {
		return coupon_type_$n;
	}

	public void setCoupon_type_$n(String coupon_type_$n) {
		this.coupon_type_$n = coupon_type_$n;
	}

	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}

	public void setCoupon_id_$n(String coupon_id_$n) {
		this.coupon_id_$n = coupon_id_$n;
	}

	public Integer getCoupon_fee_$n() {
		return coupon_fee_$n;
	}

	public void setCoupon_fee_$n(Integer coupon_fee_$n) {
		this.coupon_fee_$n = coupon_fee_$n;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
}