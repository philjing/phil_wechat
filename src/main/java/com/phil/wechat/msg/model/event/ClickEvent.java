package com.phil.wechat.msg.model.event;

/**
 * 自定义菜单事件
 * @author phil
 * @data  2017年3月26日
 *
 */
public class ClickEvent extends AbstractEvent {
	
	private static final long serialVersionUID = -2289065774997380932L;
	//事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	@Override
	public String setEvent() {
		return "CLICK";
	}
}
