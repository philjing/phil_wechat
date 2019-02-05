package com.phil.wechat.pay.model.response;

import com.phil.modules.annotation.NotRequire;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一下单API中提交的参数notify_url返回的结果
 * 
 * @author phil
 * @date 2017年6月27日
 *
 */
@Getter
@Setter
public class PayNotifyResult extends AbstractPayResult {

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
}