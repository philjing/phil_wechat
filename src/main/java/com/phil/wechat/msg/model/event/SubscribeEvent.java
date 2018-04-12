package com.phil.wechat.msg.model.event;

/**
 * 关注/取消关注事件 and 用户未关注时，进行关注后的事件推送）
 * 
 * @author phil
 * @data 2017年3月26日
 *
 */
public class SubscribeEvent extends AbstractEvent {

	private static final long serialVersionUID = 4289375375495125169L;
	private String EventKey; // 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	private String Ticket; // 二维码的ticket，可用来换取二维码图片

	@Override
	public String setEvent() {
		return "subscribe";
	}

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
}
