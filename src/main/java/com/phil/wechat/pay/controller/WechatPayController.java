package com.phil.wechat.pay.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import com.phil.modules.config.WechatConfig;
import com.phil.modules.constant.SystemConstant;
import com.phil.modules.util.DateTimeUtil;
import com.phil.modules.util.HttpReqUtil;
import com.phil.modules.util.MsgUtil;
import com.phil.modules.util.PayUtil;
import com.phil.modules.util.SignatureUtil;
import com.phil.modules.util.XmlUtil;
import com.phil.wechat.base.controller.BaseController;
import com.phil.wechat.pay.constant.PayConstant;
import com.phil.wechat.pay.model.rep.PayShortUrlParams;
import com.phil.wechat.pay.model.rep.UnifiedOrderParams;
import com.phil.wechat.pay.model.resp.JsPayResult;
import com.phil.wechat.pay.model.resp.PayShortUrlResult;
import com.phil.wechat.pay.model.resp.UnifiedOrderResult;

/**
 * 微信支付三种方式
 * 
 * @author phil
 * @date 2017年6月27日
 */
@Controller
@RequestMapping("api/wxpay")
public class WechatPayController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// public static int defaultWidthAndHeight = 200;

	@GetMapping("/payhtml")
	public String oauthBind(Map<String, Object> data) throws Exception {
		UnifiedOrderParams params = new UnifiedOrderParams();
		data.put("params", params);
		return "wechat/pay/h5pay";
	}

	/**
	 * 微信内H5调起支付
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@PostMapping("jspay")
	public Map<String, Object> jsPay(@ModelAttribute(value = "params") UnifiedOrderParams params) {
		Map<String, Object> data = new HashMap<>();
		JsPayResult result = null;
		if (StringUtils.isEmpty(params) || StringUtils.isEmpty(params.getOpenid())) {
			data.put("code", -1);
			data.put("msg", "支付数据错误");
			return data;
		}
		logger.debug("****正在支付的openId****{}", params.getOpenid());
		// 统一下单
		String out_trade_no = PayUtil.createOutTradeNo();
		// int total_fee = 1; // 产品价格1分钱,用于测试
		String spbill_create_ip = HttpReqUtil.getRemortIP(this.getRequest());
		logger.debug("支付IP {} ", spbill_create_ip);
		String nonce_str = PayUtil.createNonceStr(); // 随机数据
		// 参数组装
		UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
		unifiedOrderParams.setAppid(WechatConfig.APP_ID);// 必须
		unifiedOrderParams.setMch_id(PayConstant.MCH_ID);// 必须
		unifiedOrderParams.setOut_trade_no(out_trade_no);// 必须
		unifiedOrderParams.setBody(params.getBody());// 必须 微信支付-支付测试
		unifiedOrderParams.setTotal_fee(params.getTotal_fee()); // 必须
		unifiedOrderParams.setNonce_str(nonce_str); // 必须
		unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
		unifiedOrderParams.setTrade_type("JSAPI"); // 必须
		unifiedOrderParams.setOpenid(params.getOpenid());
		unifiedOrderParams.setNotify_url(WechatConfig.NOTIFY_URL);// 异步通知url
		// 统一下单 请求的Xml(正常的xml格式)
		String unifiedXmL = MsgUtil.abstractPayToXml(unifiedOrderParams);// 签名并入util
		// 返回<![CDATA[SUCCESS]]>格式的XML
		String unifiedOrderResultXmL = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD,
				WechatConfig.UNIFIED_ORDER_URL, null, unifiedXmL);
		// 进行签名校验
		try {
			if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedOrderResultXmL)) {
				String timeStamp = PayUtil.createTimeStamp();
				// 统一下单响应
				UnifiedOrderResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedOrderResultXmL,
						UnifiedOrderResult.class);
				result = new JsPayResult();
				result.setAppId(WechatConfig.APP_ID);
				result.setTimeStamp(timeStamp);
				result.setNonceStr(unifiedOrderResult.getNonce_str());// 直接用返回的
				/**** prepay_id 2小时内都有效，再次支付方法自己重写 ****/
				result.setPackageStr("prepay_id=" + unifiedOrderResult.getPrepay_id());
				/**** 用对象进行签名 ****/
				String paySign = SignatureUtil.createSign(result, PayConstant.API_KEY,
						SystemConstant.DEFAULT_CHARACTER_ENCODING);
				result.setPaySign(paySign);
				result.setResultCode(unifiedOrderResult.getResult_code());
				data.put("code", 0);
				data.put("msg", "支付成功");
				data.put("data", result);
			} else {
				data.put("code", -1);
				data.put("msg", "支付签名验证错误");
				logger.debug("签名验证错误");
			}
		} catch (ParserConfigurationException | IOException | SAXException | DocumentException e) {
			data.put("code", -1);
			data.put("msg", "支付失败");
			logger.debug(e.getMessage());
		}
		return data;
	}

	/**
	 * 扫码支付模式一生成二维码
	 * 
	 * @param productId 商品ID
	 * @throws IOException
	 */
	@GetMapping("payone")
	public Map<String, Object> payone(String productId) {
		Map<String, Object> data = new HashMap<>();
		String nonce_str = PayUtil.createNonceStr();
		// String product_id = "product_001"; // 推荐根据商品ID生成
		TreeMap<String, Object> packageParams = new TreeMap<>();
		packageParams.put("appid", WechatConfig.APP_ID);
		packageParams.put("mch_id", PayConstant.MCH_ID);
		packageParams.put("product_id", productId);
		packageParams.put("time_stamp", PayUtil.createTimeStamp());
		packageParams.put("nonce_str", nonce_str);
		String str_url = PayUtil.createPayImageUrl(packageParams);
		String sign = SignatureUtil.createSign(packageParams, PayConstant.API_KEY,
				SystemConstant.DEFAULT_CHARACTER_ENCODING);
		packageParams.put("sign", sign);
		String payurl = "weixin://wxpay/bizpayurl?sign=" + sign + str_url;
		logger.debug("payurl is {}", payurl);
		/**** 转成短链接 ****/
		PayShortUrlParams payShortUrlParams = new PayShortUrlParams();
		payShortUrlParams.setAppid(WechatConfig.APP_ID);
		payShortUrlParams.setMch_id(PayConstant.MCH_ID);
		payShortUrlParams.setLong_url(payurl);
		payShortUrlParams.setNonce_str(nonce_str);
		String urlSign = SignatureUtil.createSign(payShortUrlParams, PayConstant.API_KEY,
				SystemConstant.DEFAULT_CHARACTER_ENCODING);
		payShortUrlParams.setSign(urlSign);
		String longXml = XmlUtil.toSplitXml(payShortUrlParams);
		String shortResult = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD, WechatConfig.PAY_SHORT_URL, null,
				longXml);
		PayShortUrlResult payShortUrlResult = XmlUtil.getObjectFromXML(shortResult, PayShortUrlResult.class);
		if (Objects.equals("SUCCESS", payShortUrlResult.getReturn_code())) {
			payurl = payShortUrlResult.getShort_url();
		} else {
			logger.debug("错误信息" + payShortUrlResult.getReturn_msg());
		}
		/**** 生成 二维码图片自行实现 ****/
		
		return data;
	}

	/**
	 * 扫码模式二
	 * 
	 * @param params
	 * @throws Exception
	 */
	@RequestMapping("paytwo")
	public Map<String, Object> paytwo(UnifiedOrderParams params) throws Exception {
		Map<String, Object> data = new HashMap<>();
		// 商户后台系统根据用户选购的商品生成订单。
		String out_trade_no = PayUtil.createNonceStr();
		String product_id = DateTimeUtil.getCurrTime();// 根据自己的逻辑生成
		// int total_fee = 1; // 1分作测试
		// String attach = "支付测试";
		// String body = "微信支付-支付测试";
		String nonce_str = PayUtil.createNonceStr();
		String spbill_create_ip = HttpReqUtil.getRemortIP(this.getRequest()); // 获取IP
		UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
		unifiedOrderParams.setAppid(WechatConfig.APP_ID);// 必须
		unifiedOrderParams.setMch_id(PayConstant.MCH_ID);// 必须
		unifiedOrderParams.setOut_trade_no(out_trade_no);
		unifiedOrderParams.setBody(params.getBody());
		unifiedOrderParams.setAttach(params.getAttach());
		unifiedOrderParams.setProduct_id(product_id);// 必须
		unifiedOrderParams.setTotal_fee(params.getTotal_fee());// 必须
		unifiedOrderParams.setNonce_str(nonce_str); // 必须
		unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
		unifiedOrderParams.setTrade_type("NATIVE");// 必须
		unifiedOrderParams.setNotify_url(WechatConfig.NOTIFY_URL); // 异步通知URL
		// 统一下单 请求的Xml(除detail外不需要<![CDATA[product_001]]>格式)
		String unifiedXmL = MsgUtil.abstractPayToXml(unifiedOrderParams); // 签名并入util
		// logger.debug("统一下单 请求的Xml"+unifiedXmL);
		// 统一下单 返回的xml(<![CDATA[product_001]]>格式)
		String unifiedResultXmL = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD,
				WechatConfig.UNIFIED_ORDER_URL, null, unifiedXmL);
		// logger.debug("统一下单 返回的xml("+unifiedResultXmL);
		// 统一下单返回 验证签名
		if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedResultXmL)) {
			UnifiedOrderResult unifiedOrderResult = XmlUtil.getObjectFromXML(unifiedResultXmL,
					UnifiedOrderResult.class);
			if (Objects.equals("SUCCESS", unifiedOrderResult.getReturn_code())
					&& Objects.equals("SUCCESS", unifiedOrderResult.getResult_code())) {
				/**** 根据unifiedOrderResult.getCode_url()生成有效期为2小时的二维码 ****/

				/**** 根据product_id再次支付方法自己写 ****/
			}
		} else {
			logger.debug("签名验证错误");
		}
		return data;
	}
}