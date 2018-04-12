package com.phil.wechat.msg.model.req;

/**
 * 文本消息
 * @author phil
 * @date  2017年6月30日
 *
 */
public class TextMsg extends AbstractMsg {

	private static final long serialVersionUID = -1764016801417503409L;
	private String Content; // 文本消息

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String SetMsgType() {
		return "text";
	}
}
