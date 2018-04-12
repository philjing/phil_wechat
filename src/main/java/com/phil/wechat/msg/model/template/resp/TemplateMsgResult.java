package com.phil.wechat.msg.model.template.resp;

import com.phil.wechat.base.result.ResultState;

/**
 * 模板消息 返回的结果
 * @author phil
 * @date 2017年6月30日
 *
 */
public class TemplateMsgResult extends ResultState {

	private String msgid; // 消息id(发送模板消息)

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
}
