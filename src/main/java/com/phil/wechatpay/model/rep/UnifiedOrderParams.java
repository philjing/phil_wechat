package com.phil.wechatpay.model.rep;

import java.io.Serializable;

import com.phil.common.annotation.NotRequire;

/**
 * 统一下单请求参数
 * @author phil
 * @date  2017年6月27日
 * 
 */
public class UnifiedOrderParams extends AbstractPayParams implements Serializable {
	/**
	 * 
	 */
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

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScene_info() {
		return scene_info;
	}

	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}

}