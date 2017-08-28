package com.phil.wechatauth.model.req;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.phil.common.params.AbstractParams;

/**
 * 获取授权请求token的请求参数
 * @author phil
 * @date  2017年7月2日
 *
 */
public class AuthTokenParams extends AbstractParams implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4652953400751046159L;
	
	private String appid; //公众号的唯一标识
	private String secret; //公众号的appsecret
	private String code; //填写第一步获取的code参数
	private String grant_type = "authorization_code";

	public AuthTokenParams() {
		super();
	}

	public AuthTokenParams(String appid, String secret, String code, String grant_type) {
		super();
		this.appid = appid;
		this.secret = secret;
		this.code = code;
		this.grant_type = grant_type;
	}
	
	/**
	 * 参数组装
	 * @return
	 */
	public Map<String, String> getParams() {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("appid", this.appid);
		params.put("secret", this.secret);
		params.put("code", this.code);
		params.put("grant_type", this.grant_type);
		return params;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}
}
