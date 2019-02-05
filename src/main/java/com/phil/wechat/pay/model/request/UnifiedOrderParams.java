package com.phil.wechat.pay.model.request;

import com.phil.modules.annotation.NotRequire;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一下单请求参数
 * @author phil
 * @date  2017年6月27日
 * 
 */
@Getter
@Setter
@ToString
public class UnifiedOrderParams extends AbstractPayParams implements Serializable {

	private static final long serialVersionUID = -7642108447915413137L;

	@NotRequire
    private String device_info; // 设备号 可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"

	private String body; // 商品描述

	@NotRequire
	private String detail; // 商品详情

	@NotRequire
	private String attach; // 附加数据

	private String out_trade_no; // 商户订单号

	@NotRequire
	private String fee_type; // 货币类型 默认为人民币CNY

	private Integer total_fee; // 总金额 传入int型的数据

	private String spbill_create_ip;// 终端ip

	@NotRequire
	private String time_start; // 交易起始时间 订单生成时间

	@NotRequire
	private String time_expire; // 交易结束时间 订单失效时间

	@NotRequire
	private String goods_tag; // 订单优惠标记

	private String notify_url; // 通知url

	private String trade_type; // 交易类型 JSAPI，NATIVE，APP

	private String product_id; // 商品id trade_type=NATIVE时（即扫码支付），此参数必传

	@NotRequire
	private String limit_pay; // 指定支付方式 no_credit--可限制用户不能使用信用卡支付

	private String openid; // 用户标识(trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识)

	@NotRequire
	private String scene_info; // 该字段用于统一下单时上报场景信息，目前支持上报实际门店信息 格式{"store_id":// "SZT10000", "store_name":"腾讯大厦腾大餐厅"}

}