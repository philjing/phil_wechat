package com.phil.wechat.auth.controller;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phil.modules.config.SystemConfig;
import com.phil.modules.config.WechatConfig;
import com.phil.modules.util.DateTimeUtil;
import com.phil.modules.util.MD5Util;
import com.phil.modules.util.PayUtil;
import com.phil.modules.util.SignatureUtil;
import com.phil.wechat.auth.model.req.AuthCodeParams;
import com.phil.wechat.auth.model.resp.JsSDKConfig;
import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.base.controller.BaseController;

/**
 * JS-SDK
 * 
 * @author phil
 * @date 2017年8月21日
 *
 */
@Controller
@RequestMapping("api/auth")
public class WechatAuthController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WechatAuthService wechatAuthService;

//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 获取地理位置
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getLocation")
	public String getLocation(Map<String, Object> data) throws Exception {
		JsSDKConfig jssdkconfig = new JsSDKConfig();
		jssdkconfig.setAppId(WechatConfig.APP_ID);
		jssdkconfig.setTimestamp(DateTimeUtil.currentTime());
		jssdkconfig.setNoncestr(PayUtil.createNonceStr());
		SortedMap<Object, Object> params = new TreeMap<Object, Object>();
//		params.put("jsapi_ticket", wechatAuthService
//				.getTicket(ObjectUtil.toString(redisTemplate.opsForValue().get(WechatConfig.APP_ID), false)));
		params.put("noncestr", jssdkconfig.getNoncestr());
		params.put("timestamp", jssdkconfig.getTimestamp());
		params.put("url", this.getRequest().getRequestURL().toString());
		String signature = SignatureUtil.createSha1Sign(params, null, SystemConfig.DEFAULT_CHARACTER_ENCODING);
		jssdkconfig.setSignature(signature);
		logger.debug("JsSDKConfig {}", jssdkconfig);
		data.put("data", jssdkconfig);
		return "auth/getLocation";
	}

	/**
	 * 返回回调URL给需要的页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/oauthBind")
	public String oauthBind(Map<String,Object> data) throws Exception {
		AuthCodeParams authCodeParams = new AuthCodeParams();
		String redirect_uri = "http://www.liebesy.com/oauth/bindWxPhone.shtml";
		authCodeParams.setRedirect_uri(redirect_uri);
		authCodeParams.setAppid(WechatConfig.APP_ID);
		authCodeParams.setScope(AuthCodeParams.SCOPE_SNSAPIBASE);// 采用静默授权
		authCodeParams.setState(MD5Util.MD5Encode("ceshi", "")); //防止被攻击,用于校验
		String url = wechatAuthService.getAuthPath(authCodeParams, WechatConfig.AUTHORIZE_OAUTH_URL);
		data.put("url", url);
		return null;
	}
}