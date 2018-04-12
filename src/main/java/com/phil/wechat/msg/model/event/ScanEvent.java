package com.phil.wechat.msg.model.event;

/**
 * 扫描带参数二维码事件 用户已关注时，进行关注后的事件推送 事件KEY值
 * 
 * @author phil
 * @data 2017年3月26日
 *
 */
public class ScanEvent extends AbstractEvent {

	private static final long serialVersionUID = -1734638114427074174L;
	private String EventKey; // 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
	private String Ticket; // 二维码的ticket，可用来换取二维码图片

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	@Override
	public String setEvent() {
		return "SCAN";
	}
}
