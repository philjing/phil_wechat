package com.phil.wechat.auth.service;

import com.phil.wechat.auth.model.resp.AuthAccessToken;
import com.phil.wechat.auth.model.resp.AuthUserInfo;
import com.phil.wechat.base.param.AbstractParams;
import com.phil.wechat.base.result.ResultState;

/**
 * @author phil
 * @date  2017年8月5日
 *
 */
public interface WechatAuthService {
	
	/**
	 * 获取授权请求url
	 * @param basic
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getAuthPath(AbstractParams basic, String url) throws Exception;
	
	/**
	 * 获取网页授权凭证
	 * @param basic
	 * @param url
	 * @return
	 */
	public AuthAccessToken getAuthAccessToken(AbstractParams basic, String url);

	/**
	 * 刷新网页授权验证
	 * @param basic  参数
	 * @param url 请求路径
	 * @return
	 */
	public AuthAccessToken refreshAuthAccessToken(AbstractParams basic, String url) ;
	
	/**
	 * 通过网页授权获取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public AuthUserInfo getAuthUserInfo(String accessToken, String openid);
	
	/**
	 * 检验授权凭证（access_token）是否有效
	 * @param accessToken 网页授权接口调用凭证
	 * @param openid		用户的唯一标识
	 * @return  { "errcode":0,"errmsg":"ok"}表示成功     { "errcode":40003,"errmsg":"invalid openid"}失败
	 */
	public ResultState authToken(String accessToken,String openid);

	/**
	 * 获取jsapi_ticket 调用微信JS接口的临时票据
	 * @return
	 */
	public String getTicket(String accessToken);

}
