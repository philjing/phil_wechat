package com.phil.wechatauth.service.impl;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.JsonSyntaxException;
import com.phil.common.config.WechatConfig;
import com.phil.common.params.AbstractParams;
import com.phil.common.result.ResultState;
import com.phil.common.util.HttpReqUtil;
import com.phil.common.util.JsonUtil;
import com.phil.wechatauth.entity.WechatAccessToken;
import com.phil.wechatauth.model.resp.AuthAccessToken;
import com.phil.wechatauth.model.resp.AuthUserInfo;
import com.phil.wechatauth.service.WechatAuthService;

/**
 * AuthToken Service
 * @author phil
 * @date 2017年7月9日
 *
 */
@Service
public class WechatAuthServiceImpl implements WechatAuthService {

	private static final Logger logger = Logger.getLogger(WechatAuthServiceImpl.class);
	
	/**
	 * 获取授权token
	 * @param key  应用key
	 * @param secret  应用密匙
	 * @return
	 */
	public String getAccessToken(String key, String secret) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("grant_type", "client_credential");
		map.put("appid", key);
		map.put("secret", secret);
		String result = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.GET_METHOD, WechatConfig.TOKEN_PATH, map, null);
		WechatAccessToken accessToken = JsonUtil.fromJson(result, WechatAccessToken.class);
		return accessToken==null?null:accessToken.getToken();
	}
	
	/**
	 * 获取授权请求path
	 * @param basic
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String getAuthPath(AbstractParams basic, String path) throws Exception{
		Map<String,String> params = basic.getParams();
		path = HttpReqUtil.setParmas(params, path,"")+"#wechat_redirect";
		return path;
	}
	
	/**
	 * 获取网页授权凭证
	 * @param basic
	 * @param path
	 * @return
	 */
	public AuthAccessToken getAuthAccessToken(AbstractParams basic, String path) {
		AuthAccessToken authAccessToken = null;
		try {
			String result = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.GET_METHOD, path, basic.getParams(), null);
			if(result != null){		
				authAccessToken =JsonUtil.fromJson(result, AuthAccessToken.class);
			}
		} catch (Exception e) {
			authAccessToken = null;
		}
		return authAccessToken;
	}

	/**
	 * 刷新网页授权验证
	 * @param basic  参数
	 * @param path 请求路径
	 * @return
	 */
	public String refreshAuthAccessToken(AbstractParams basic, String path) {	
		AuthAccessToken authAccessToken = null;
		try {
			String result = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.GET_METHOD, path, basic.getParams(), null);
			if(result != null){				
				authAccessToken = JsonUtil.fromJson(result, AuthAccessToken.class);
			}
		} catch (Exception e) {
			authAccessToken = null;
		}
		return authAccessToken==null?null:authAccessToken.getAccess_token();
	}

	/**
	 * 通过网页授权获取用户信息
	 * @param oauth2Token
	 * @return
	 */
	public AuthUserInfo getAuthUserInfo(String accessToken, String openId) {
		AuthUserInfo authUserInfo = null;	
		//通过网页授权获取用户信息
		Map<String, String> params = new TreeMap<String, String>();
		params.put("openid", openId);
		params.put("access_token", accessToken);
		String result = HttpReqUtil.HttpsDefaultExecute(HttpReqUtil.GET_METHOD, WechatConfig.USERINFO_URL, params, null);		
		if(null != result){
			try {
				authUserInfo = JsonUtil.fromJson(result, AuthUserInfo.class);
			} catch (JsonSyntaxException e) {
				logger.info("transfer exception");				
			} 
		}
		return authUserInfo;
	}
	
	/**
	 *  检验授权凭证（access_token）是否有效
	 * @param accessToken 网页授权接口调用凭证
	 * @param openid		用户的唯一标识
	 * @return  { "errcode":0,"errmsg":"ok"}表示成功     { "errcode":40003,"errmsg":"invalid openid"}失败
	 */
	public ResultState authToken(String accessToken,String openid){
		ResultState state = null;
		Map<String,String> params = new TreeMap<String,String>();
		params.put("access_token",accessToken);
		params.put("openid", openid);
		String jsonResult = HttpReqUtil.HttpDefaultExecute(HttpReqUtil.GET_METHOD, WechatConfig.SNS_AUTH_URL, params,"");
		if(jsonResult!=null){
			state = JsonUtil.fromJson(jsonResult, ResultState.class);
		}
		return state;
	}
}