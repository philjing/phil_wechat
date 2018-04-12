package com.phil.wechat.auth.model.resp;

import com.phil.wechat.base.result.ResultState;

/**
 * jsapi_ticket是公众号用于调用微信JS接口的临时票据
 * 
 * @author phil
 * @date  2017年8月21日
 */
public class JsapiTicket extends ResultState {

	private String ticket; //jsapi_ticket
	
	private String expires_in;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
}