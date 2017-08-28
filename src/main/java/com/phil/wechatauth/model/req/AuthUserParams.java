package com.phil.wechatauth.model.req;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.phil.common.params.AbstractParams;

/**
 * 获取用户信息请求
 * @author phil
 * @date  2017年7月2日
 *
 */
public class AuthUserParams extends AbstractParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 66535717787322321L;
	
	private String accessToken;
	private String openid;
	private String lang;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * 参数组装
	 * @return
	 */
	public Map<String, String> getParams() throws Exception {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("access_token", this.accessToken);
		params.put("openid", this.openid);
		params.put("lang", this.lang);
		return params;
	}

}
