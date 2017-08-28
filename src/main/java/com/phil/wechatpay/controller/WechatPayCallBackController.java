package com.phil.wechatpay.controller;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phil.common.config.SystemConfig;
import com.phil.common.config.WechatConfig;
import com.phil.common.util.HttpReqUtil;
import com.phil.common.util.PayUtil;
import com.phil.common.util.SignatureUtil;
import com.phil.common.util.XmlUtil;
import com.phil.wechatpay.model.rep.PayCallBackParams;
import com.phil.wechatpay.model.rep.UnifiedOrderParams;
import com.phil.wechatpay.model.resp.PayCallBackResult;
import com.phil.wechatpay.model.resp.UnifiedOrderResult;

/**
 * 扫码模式一回调
 * 
 * @author phil
 * @date 2017年6月27日
 *
 */
@Controller
@RequestMapping("/wxpay/")
public class WechatPayCallBackController {

	private static final Logger logger = Logger.getLogger(WechatPayCallBackController.class);

	@RequestMapping("/callback")//payone
	public void callBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resXml = "";// 反馈给微信服务器
		// 微信支付系统发送的数据（<![CDATA[product_001]]>格式）
		String xml = HttpReqUtil.inputStreamToString(request.getInputStream());
		// logger.info("微信支付系统发送的数据"+xml);
		/**** 微信支付系统发送的数据其实就是回调地址输入的参数Xml****/
		// 验证签名
		if (SignatureUtil.checkIsSignValidFromWeiXin(xml, WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING)) {
			// 转换成输入参数，
			PayCallBackParams payCallBackParams = XmlUtil.getObjectFromXML(xml, PayCallBackParams.class);
			// appid openid mch_id is_subscribe nonce_str product_id sign
			// 统一下单
			String openid = payCallBackParams.getOpenid();
			String product_id = payCallBackParams.getProduct_id();
			/**** product_id 等 生成自己系统的订单****/
			int total_fee = 1; // 根据product_id算出价格
			String out_trade_no = PayUtil.createOutTradeNo(); //生成订单号	
			String body = product_id; // 商品名称设置为product_id
			String attach = "XXX店"; // 附加数据
			String nonce_str = PayUtil.createNonceStr();
			String spbill_create_ip = HttpReqUtil.getRemortIP(request);
			// 组装统一下单的请求参数
			UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
			unifiedOrderParams.setAppid(WechatConfig.APP_ID);// 必须
			unifiedOrderParams.setMch_id(WechatConfig.MCH_ID);// 必须
			unifiedOrderParams.setOut_trade_no(out_trade_no);
			unifiedOrderParams.setBody(body);
			unifiedOrderParams.setAttach(attach);
			unifiedOrderParams.setTotal_fee(total_fee);// 必须
			unifiedOrderParams.setNonce_str(nonce_str); // 必须
			unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
			unifiedOrderParams.setTrade_type("NATIVE");// 必须
			unifiedOrderParams.setOpenid(openid);
			unifiedOrderParams.setNotify_url(WechatConfig.NOTIFY_URL); // 异步通知URL
			// 签名
			String sign = SignatureUtil.createSign(unifiedOrderParams, WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
			unifiedOrderParams.setSign(sign);
			// 统一下单 请求的Xml
			String unifiedXmL = XmlUtil.toSplitXml(unifiedOrderParams);
			// 统一下单 返回的xml
			String unifiedOrderResultXmL = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.POST_METHOD,WechatConfig.UNIFIED_ORDER_URL, null, unifiedXmL);
			// 统一下单返回 验证签名
			if (SignatureUtil.checkIsSignValidFromWeiXin(unifiedOrderResultXmL, WechatConfig.API_KEY,SystemConfig.CHARACTER_ENCODING)) {
				UnifiedOrderResult unifiedOrderResult = (UnifiedOrderResult) XmlUtil.getObjectFromXML(unifiedOrderResultXmL, UnifiedOrderResult.class);
				if("SUCCESS".equals(unifiedOrderResult.getReturn_code()) && "SUCCESS".equals(unifiedOrderResult.getResult_code())){
					PayCallBackResult payCallBackResult = new PayCallBackResult();
					payCallBackResult.setReturn_code(unifiedOrderResult.getReturn_code());
					payCallBackResult.setAppid(WechatConfig.APP_ID);
					payCallBackResult.setMch_id(WechatConfig.MCH_ID);
					payCallBackResult.setNonce_str(unifiedOrderResult.getNonce_str());//直接用微信返回的
					/**** prepay_id 2小时内都有效，根据product_id再次支付方法自己写 ****/
					payCallBackResult.setPrepay_id(unifiedOrderResult.getPrepay_id());
					payCallBackResult.setResult_code(unifiedOrderResult.getResult_code());
					String callsign = SignatureUtil.createSign(payCallBackResult, WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
					payCallBackResult.setSign(callsign);
					resXml = XmlUtil.toXml(payCallBackResult).replace("__", "_");
					//将数据包返回给微信支付系统处理
				}
			} else {
				logger.info("签名验证错误");
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"+ "<return_msg><![CDATA[签名验证错误]]></return_msg>" + "</xml> ";
			}
		} else {
			logger.info("签名验证错误");
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"+ "<return_msg><![CDATA[签名验证错误]]></return_msg>" + "</xml> ";
		}
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(resXml.getBytes());
		out.flush();
		IOUtils.closeQuietly(out);
	}
}