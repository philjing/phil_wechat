package com.phil.wechatpay.controller;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phil.common.config.SystemConfig;
import com.phil.common.config.WechatConfig;
import com.phil.common.util.DateTimeUtil;
import com.phil.common.util.HttpReqUtil;
import com.phil.common.util.PayUtil;
import com.phil.common.util.QRCodeGenerator;
import com.phil.common.util.SignatureUtil;
import com.phil.common.util.XmlUtil;
import com.phil.wechatpay.model.rep.PayShortUrlParams;
import com.phil.wechatpay.model.rep.UnifiedOrderParams;
import com.phil.wechatpay.model.resp.JsPayResult;
import com.phil.wechatpay.model.resp.PayShortUrlResult;
import com.phil.wechatpay.model.resp.UnifiedOrderResult;
import com.phil.wechatpay.service.WechatPayService;

/**
 * 微信支付三种方式
 * 
 * @author phil
 * @date 2017年6月27日
 *
 */
@Controller
@RequestMapping("/wxpay/")
public class WechatPayController {

	private static final Logger logger = Logger.getLogger(WechatPayController.class);

	public static int defaultWidthAndHeight = 200;
	
	@Autowired
	private WechatPayService wechatPayService;
	
	/**
	 * 微信内H5调起支付
	 * 
	 * @param request
	 * @param response
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("jspay")
	public JsPayResult jsPay(HttpServletRequest request, HttpServletResponse response, String openId) throws Exception {
		JsPayResult result = null;
		logger.info("****正在支付的openId****" + openId);
		// 统一下单
		String out_trade_no = PayUtil.createOutTradeNo();
		int total_fee = 1; // 产品价格1分钱,用于测试
		String spbill_create_ip = HttpReqUtil.getRemortIP(request);
		logger.info("支付IP" + spbill_create_ip);
		String nonce_str = PayUtil.createNonceStr(); // 随机数据
		//参数组装
		UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
		unifiedOrderParams.setAppid(WechatConfig.APP_ID);// 必须
		unifiedOrderParams.setMch_id(WechatConfig.MCH_ID);// 必须
		unifiedOrderParams.setOut_trade_no(out_trade_no);// 必须
		unifiedOrderParams.setBody("微信支付-支付测试");// 必须
		unifiedOrderParams.setTotal_fee(total_fee); // 必须
		unifiedOrderParams.setNonce_str(nonce_str); // 必须
		unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
		unifiedOrderParams.setTrade_type("JSAPI"); // 必须
		unifiedOrderParams.setOpenid(openId);
		unifiedOrderParams.setNotify_url(WechatConfig.NOTIFY_URL);// 异步通知url
		// 统一下单 请求的Xml(正常的xml格式)
		String unifiedXmL = wechatPayService.abstractPayToXml(unifiedOrderParams);////签名并入service
		// 返回<![CDATA[SUCCESS]]>格式的XML
		String unifiedOrderResultXmL = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST_METHOD,WechatConfig.UNIFIED_ORDER_URL, null, unifiedXmL);
		// 进行签名校验
		if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedOrderResultXmL)) {
			String timeStamp = PayUtil.createTimeStamp();
			//统一下单响应
			UnifiedOrderResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedOrderResultXmL, UnifiedOrderResult.class);
			/**** 用map方式进行签名 ****/
			// SortedMap<Object, Object> signMap = new TreeMap<Object,
			// Object>();
			// signMap.put("appId", WeiXinConfig.APP_ID); // 必须
			// signMap.put("timeStamp", timeStamp);
			// signMap.put("nonceStr", nonceStr);
			// signMap.put("signType", "MD5");
			// signMap.put("package", "prepay_id=" + prepay_id);
			// String paySign = SignatureUtil.createSign(signMap, key, SystemConfig.CHARACTER_ENCODING);
			result = new JsPayResult();
			result.setAppId(WechatConfig.APP_ID);
			result.setTimeStamp(timeStamp);
			result.setNonceStr(unifiedOrderResult.getNonce_str());//直接用返回的
			/**** prepay_id 2小时内都有效，再次支付方法自己重写 ****/
			result.setPackageStr("prepay_id=" + unifiedOrderResult.getPrepay_id());
			/**** 用对象进行签名 ****/
			String paySign = SignatureUtil.createSign(result, WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
			result.setPaySign(paySign);
			result.setResultCode(unifiedOrderResult.getResult_code());
		} else {
			logger.info("签名验证错误");
		}
		/**** 返回对象给页面 ****/
		return result;
	}

	/**
	 * 扫码支付模式一生成二维码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("qrcode")
	public String createPayImage(HttpServletRequest request, HttpServletResponse response) {
		String nonce_str = PayUtil.createNonceStr();
		String product_id = "product_001"; // 推荐根据商品ID生成
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", WechatConfig.APP_ID);
		packageParams.put("mch_id", WechatConfig.MCH_ID);
		packageParams.put("product_id", product_id);
		packageParams.put("time_stamp", PayUtil.createTimeStamp());
		packageParams.put("nonce_str", nonce_str);
		String str = PayUtil.createPayImageUrl(packageParams);
		String sign = SignatureUtil.createSign(packageParams,WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
		packageParams.put("sign", sign);
		String payurl = "weixin://wxpay/bizpayurl?sign=" + sign + str;
		logger.info("payurl is " + payurl);	
		/**** 转成短链接 ****/
		PayShortUrlParams payShortUrlParams = new PayShortUrlParams();
		payShortUrlParams.setAppid(WechatConfig.APP_ID);
		payShortUrlParams.setMch_id(WechatConfig.MCH_ID);
		payShortUrlParams.setLong_url(payurl);
		payShortUrlParams.setNonce_str(nonce_str);
		String urlSign = SignatureUtil.createSign(payShortUrlParams,WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
		payShortUrlParams.setSign(urlSign);
		String longXml = XmlUtil.toSplitXml(payShortUrlParams);
		String shortResult = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST_METHOD, WechatConfig.PAY_SHORT_URL, null, longXml);
		PayShortUrlResult payShortUrlResult = XmlUtil.getObjectFromXML(shortResult, PayShortUrlResult.class);
		if("SUCCESS".equals(payShortUrlResult.getReturn_code())){
			payurl = payShortUrlResult.getShort_url();
		}else{
			logger.debug("错误信息"+payShortUrlResult.getReturn_msg());
		}
		/**** 生成 二维码图片自己实现****/
		return null;
	}

	/**
	 * 扫码模式二
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("paytwo")
	public String paytwo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 商户后台系统根据用户选购的商品生成订单。
		String out_trade_no = PayUtil.createNonceStr();
		String product_id = DateTimeUtil.getCurrTime();// 根据自己的逻辑生成
		int total_fee = 1; // 1分作测试
		String attach = "支付测试";
		String body = "微信支付-支付测试";
		String nonce_str = PayUtil.createNonceStr();
		String spbill_create_ip = HttpReqUtil.getRemortIP(request); // 获取IP
		UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
		unifiedOrderParams.setAppid(WechatConfig.APP_ID);// 必须
		unifiedOrderParams.setMch_id(WechatConfig.MCH_ID);// 必须
		unifiedOrderParams.setOut_trade_no(out_trade_no);
		unifiedOrderParams.setBody(body);
		unifiedOrderParams.setAttach(attach);
		unifiedOrderParams.setProduct_id(product_id);// 必须
		unifiedOrderParams.setTotal_fee(total_fee);// 必须
		unifiedOrderParams.setNonce_str(nonce_str); // 必须
		unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
		unifiedOrderParams.setTrade_type("NATIVE");// 必须
		unifiedOrderParams.setNotify_url(WechatConfig.NOTIFY_URL); //异步通知URL
		// 统一下单 请求的Xml(除detail外不需要<![CDATA[product_001]]>格式)
		String unifiedXmL = wechatPayService.abstractPayToXml(unifiedOrderParams); //签名并入service
		// logger.info("统一下单 请求的Xml"+unifiedXmL);
		// 统一下单 返回的xml(<![CDATA[product_001]]>格式)
		String unifiedResultXmL = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST_METHOD, WechatConfig.UNIFIED_ORDER_URL, null, unifiedXmL);
		// logger.info("统一下单 返回的xml("+unifiedResultXmL);
		// 统一下单返回 验证签名
		if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedResultXmL)) {
			UnifiedOrderResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedResultXmL,UnifiedOrderResult.class);
			if("SUCCESS".equals(unifiedOrderResult.getReturn_code()) && "SUCCESS".equals(unifiedOrderResult.getResult_code())){
				/****根据unifiedOrderResult.getCode_url()生成有效期为2小时的二维码****/	
				
				
				/****根据product_id再次支付方法自己写****/
			}
		}else{
			logger.info("签名验证错误");
		}
		return null;
	}
	
	public static void main(String[] args){
		String nonce_str = PayUtil.createNonceStr();
		String product_id = "product_001"; // 推荐根据商品ID生成
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", WechatConfig.APP_ID);
		packageParams.put("mch_id", WechatConfig.MCH_ID);
		packageParams.put("product_id", product_id);
		packageParams.put("time_stamp", PayUtil.createTimeStamp());
		packageParams.put("nonce_str", nonce_str);
		String str = PayUtil.createPayImageUrl(packageParams);
		String sign = SignatureUtil.createSign(packageParams,WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
		packageParams.put("sign", sign);
		String payurl = "weixin://wxpay/bizpayurl?sign=" + sign + str;
		logger.info("payurl is " + payurl);	
		/**** 转成短链接 ****/
		PayShortUrlParams payShortUrlParams = new PayShortUrlParams();
		payShortUrlParams.setAppid(WechatConfig.APP_ID);
		payShortUrlParams.setMch_id(WechatConfig.MCH_ID);
		payShortUrlParams.setLong_url(payurl);
		payShortUrlParams.setNonce_str(nonce_str);
		String urlSign = SignatureUtil.createSign(payShortUrlParams,WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
		payShortUrlParams.setSign(urlSign);
		String longXml = XmlUtil.toSplitXml(payShortUrlParams);
		String shortResult = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST_METHOD, WechatConfig.PAY_SHORT_URL, null, longXml);
		PayShortUrlResult payShortUrlResult = XmlUtil.getObjectFromXML(shortResult, PayShortUrlResult.class);
		if("SUCCESS".equals(payShortUrlResult.getReturn_code())){
			payurl = payShortUrlResult.getShort_url();
			logger.info("payurl is " + payurl);	
		}else{
			logger.info("错误信息"+payShortUrlResult.getReturn_msg());
		}
		QRCodeGenerator handler = new QRCodeGenerator();
		handler.encoderQRCode(payurl,"F:/微信/"+"支付二维码"+DateTimeUtil.currentTime()+".png", "png");
		
	}
}