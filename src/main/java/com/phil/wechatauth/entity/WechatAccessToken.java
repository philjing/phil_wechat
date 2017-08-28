package com.phil.wechatauth.entity;

import java.io.Serializable;

/**
 * 微信的access_token
 * 
 * @author phil
 * @date 2017年7月9日
 *
 */
public class WechatAccessToken implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 123771404997810930L;

	private String id;

	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}