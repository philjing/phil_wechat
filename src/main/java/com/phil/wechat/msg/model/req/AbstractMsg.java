package com.phil.wechat.msg.model.req;

import java.io.Serializable;

/**
 * 基础消息类
 * 
 * @author phil
 * 
 */
public abstract class AbstractMsg implements Serializable {
	
	private static final long serialVersionUID = -6244277633057415731L;
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private String MsgType = SetMsgType(); // 消息类型 例如 /text/image
	private long CreateTime; // 消息创建时间 （整型）
	private long MsgId; // 消息id，64位整型
	
	/**
	 * 消息类型
	 * 
	 * @return
	 */
	public abstract String SetMsgType();

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	public String getMsgType() {
		return MsgType;
	}
}
