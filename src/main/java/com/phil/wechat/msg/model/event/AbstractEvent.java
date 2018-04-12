package com.phil.wechat.msg.model.event;

import java.io.Serializable;

/**
 * 事件基类
 * @author phil
 * @data 2017年3月26日
 *
 */
public abstract class AbstractEvent implements Serializable{
	
	private static final long serialVersionUID = 2819147057663245151L;
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private int CreateTime; // 消息创建时间 （整型

	private String MsgType = "event"; // 消息类型，event

	private String Event = setEvent(); // 事件类型，subscribe(订阅)、unsubscribe(取消订阅)

	public abstract String setEvent();

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

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}
}
