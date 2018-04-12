package com.phil.wechat.msg.model.resp;

import com.phil.wechat.base.result.ResultState;

/**
 * 消息 返回的结果数
 * @author phil
 * @date 2017年7月2日
 * 
 */
public class MsgResult extends ResultState{
	
	private static final long serialVersionUID = 5919613427515551136L;
	private String msg_id; // 消息id
	private String msgid; // 消息id(发送模板消息)

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}
}
