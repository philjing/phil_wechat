package com.phil.wechat.base.result;

/**
 * 微信API返回状态
 * 
 * @author phil
 * @date 2017年7月2日
 *
 */
public class ResultState extends AbstractResult {
	
	private int errcode; // 状态
	private String errmsg; //信息

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
