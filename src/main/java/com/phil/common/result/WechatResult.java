package com.phil.common.result;

/**
 * 微信返回的结果对象
 * 
 * @author phil
 * 
 */
public class WechatResult {

	public static final int NEWSMSG = 1; // 图文消息

	private boolean success;

	private int type;
	
	private Object object;
	
	private String msg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
