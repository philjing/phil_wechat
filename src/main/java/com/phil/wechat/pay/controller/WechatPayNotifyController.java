package com.phil.wechat.pay.controller;

import java.io.BufferedOutputStream;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phil.modules.util.IOUtil;
import com.phil.modules.util.SignatureUtil;
import com.phil.modules.util.XmlUtil;
import com.phil.wechat.base.controller.BaseController;
import com.phil.wechat.base.result.ResultState;
import com.phil.wechat.pay.model.resp.PayNotifyResult;

/**
 * 微信支付结果通知(统一下单参数的notify_url)
 * 
 * @author phil
 * @date 2017年6月27日
 *
 */
@RestController
@RequestMapping("/wxpay/")
public class WechatPayNotifyController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("notify")
	public ResultState acquaint() throws Exception {
		ResultState resultState = new ResultState();
		logger.debug("开始处理支付返回的请求");
		String resXml = ""; // 反馈给微信服务器
		String notifyXml = IOUtil.inputStreamToString(this.getRequest().getInputStream(), null);// 微信支付系统发送的数据（<![CDATA[product_001]]>格式）
		logger.debug("微信支付系统发送的数据" + notifyXml);
		// 验证签名
		if (SignatureUtil.checkIsSignValidFromWeiXin(notifyXml, null)) {
			PayNotifyResult notify = XmlUtil.getObjectFromXML(notifyXml, PayNotifyResult.class);
			logger.debug("支付结果 {}", notify.toString());
			if (Objects.equals("SUCCESS", notify.getResult_code())) {
				resultState.setErrcode(0);// 表示成功
				resultState.setErrmsg(notify.getResult_code());
				/**** 业务逻辑 保存openid之类的 ****/
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} else {
				resultState.setErrcode(-1);// 支付失败
				resultState.setErrmsg(notify.getErr_code_des());
				logger.debug("支付失败,错误信息：" + notify.getErr_code_des());
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA["
						+ notify.getErr_code_des() + "]]></return_msg>" + "</xml> ";
			}
		} else {
			resultState.setErrcode(-1);// 支付失败
			resultState.setErrmsg("签名验证错误");
			logger.debug("签名验证错误");
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[签名验证错误]]></return_msg>" + "</xml> ";
		}
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(this.getResponse().getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultState;
	}
}