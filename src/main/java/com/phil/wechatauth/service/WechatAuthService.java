package com.phil.wechatauth.service;

import com.phil.common.params.AbstractParams;
import com.phil.common.result.ResultState;
import com.phil.wechatauth.model.resp.AuthAccessToken;
import com.phil.wechatauth.model.resp.AuthUserInfo;

public interface WechatAuthService {

	/**
	 * 获取授权token
	 * @param key  应用key
	 * @param secret  应用密匙
	 * @return
	 */
	public String getAccessToken(String key, String secret);
	
	/**
	 * 获取授权请求path
	 * @param basic
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String getAuthPath(AbstractParams basic, String path) throws Exception;
	
	/**
	 * 获取网页授权凭证
	 * @param basic
	 * @param path
	 * @return
	 */
	public AuthAccessToken getAuthAccessToken(AbstractParams basic, String path);

	/**
	 * 刷新网页授权验证
	 * @param basic  参数
	 * @param path 请求路径
	 * @return
	 */
	public String refreshAuthAccessToken(AbstractParams basic, String path);

	/**
	 * 通过网页授权获取用户信息
	 * @param oauth2Token
	 * @return
	 */
	public AuthUserInfo getAuthUserInfo(String accessToken, String openId);
	
	/**
	 *  检验授权凭证（access_token）是否有效
	 * @param accessToken 网页授权接口调用凭证
	 * @param openid		用户的唯一标识
	 * @return  { "errcode":0,"errmsg":"ok"}表示成功     { "errcode":40003,"errmsg":"invalid openid"}失败
	 */
	public ResultState authToken(String accessToken,String openid);

}