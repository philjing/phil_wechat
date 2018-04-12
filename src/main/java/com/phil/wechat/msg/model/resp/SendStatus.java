package com.phil.wechat.msg.model.resp;

/**
 * 发送的状态
 * @author phil
 * @date 2017年7月2日
 *
 */
public class SendStatus {
	private long msg_id; // 消息的id
	private String msg_status; // 消息的状态

	public long getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(long msgId) {
		msg_id = msgId;
	}

	public String getMsg_status() {
		return msg_status;
	}

	public void setMsg_status(String msgStatus) {
		msg_status = msgStatus;
	}
}
