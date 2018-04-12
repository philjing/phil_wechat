package com.phil.wechat.msg.model.event;

/**
 * 自定义菜单跳转链接
 * 
 * @author phil
 * 
 */
public class ViewEvent extends AbstractEvent {
	
	private static final long serialVersionUID = -1515341605281809731L;
	private String EventKey; // 事件KEY值，设置的跳转URL

	@Override
	public String setEvent() {
		return "VIEW";
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
