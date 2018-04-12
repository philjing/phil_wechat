package com.phil.wechat.msg.model.req;

import java.io.Serializable;

/**
 * 消息基类
 * 
 * @author phil
 * 
 */
public class BasicMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private int CreateTime; // 消息创建时间 （整型）
	private long MsgId; // 消息id，64位整型

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

	public int getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
}
