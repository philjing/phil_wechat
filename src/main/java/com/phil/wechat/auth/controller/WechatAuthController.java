package com.phil.wechat.auth.controller;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phil.modules.config.WechatConfig;
import com.phil.modules.util.DateTimeUtil;
import com.phil.modules.util.MD5Util;
import com.phil.modules.util.PayUtil;
import com.phil.modules.util.SignatureUtil;
import com.phil.wechat.auth.model.req.AuthTokenParams;
import com.phil.wechat.auth.model.resp.AuthAccessToken;
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
@RequestMapping("wxauth")
public class WechatAuthController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WechatAuthService wechatAuthService;

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
		TreeMap<String, Object> params = new TreeMap<>();
//		params.put("jsapi_ticket", wechatAuthService.getTicket(ObjectUtil.toString(redisTemplate.opsForValue().get(WechatConfig.APP_ID), false)));
		params.put("noncestr", jssdkconfig.getNoncestr());
		params.put("timestamp", jssdkconfig.getTimestamp());
		params.put("url", this.getRequest().getRequestURL().toString());
		String signature = SignatureUtil.createSha1Sign(params, null, null);
		jssdkconfig.setSignature(signature);
		logger.debug("JsSDKConfig {}", jssdkconfig);
		data.put("data", jssdkconfig);
		return "auth/getLocation";
	}

	/** 
	 * 静默授权进入H5支付页面 
	 * @return 
	 * @throws Exception 
	 */  
	@GetMapping("preJsPay")
	public String jsPay( ) throws Exception {  
	    AuthAccessToken authAccessToken = null;  
	    String code = this.getRequest().getParameter("code");
//	    Optional<String> code_ = Optional.of(code);
//	    return code_.orElse("error");
	    if(StringUtils.isEmpty(code)){
	    	return "error";
	    }
	    String state = this.getRequest().getParameter("state");  
	    if(state.equals(MD5Util.MD5Encode("ceshi", ""))){
	        AuthTokenParams authTokenParams = new AuthTokenParams();  
	        authTokenParams.setAppid(WechatConfig.APP_ID);
	        authTokenParams.setSecret(WechatConfig.APP_SECRET);  
	        authTokenParams.setCode(code);
	        authAccessToken = wechatAuthService.getAuthAccessToken(authTokenParams, null);
	        logger.debug("正在支付的openid {} ", authAccessToken.getOpenid());  
	        return "wxpay/jspay";
	    }
	    return "error";
	}  
	
//	private String oauthUrl(String redirect_uri) throws Exception {
//		AuthCodeParams authCodeParams = new AuthCodeParams();
//		authCodeParams.setRedirect_uri(redirect_uri);
//		authCodeParams.setAppid(WechatConfig.APP_ID);
//		authCodeParams.setScope(AuthCodeParams.SCOPE_SNSAPIBASE);// 采用静默授权
//		authCodeParams.setState(MD5Util.MD5Encode("ceshi", "")); //防止被攻击,用于校验
//		String url = wechatAuthService.getAuthPath(authCodeParams, WechatConfig.AUTHORIZE_OAUTH_URL);
//		return url;
//	}
}