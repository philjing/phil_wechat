package com.phil.wechatauth.model.resp;

/**
 * 网页授权access_token
 * 
 * @author phil
 * @date 2017年7月2日
 *
 */
public class AuthAccessToken extends AccessToken {

	private String refresh_token; // 用户刷新access_token
	private String openid; // 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	private String scope; // 用户授权的作用域，使用逗号（,）分隔

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
