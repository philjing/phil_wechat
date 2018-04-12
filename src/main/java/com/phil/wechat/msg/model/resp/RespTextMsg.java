package com.phil.wechat.msg.model.resp;

/**
 * 文本消息（公众帐号 -> 普通用户）
 * @author phil
 *
 */
public class RespTextMsg extends RespAbstractMsg {
	
	//回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String setMsgType() {
		return "text";
	}
}
