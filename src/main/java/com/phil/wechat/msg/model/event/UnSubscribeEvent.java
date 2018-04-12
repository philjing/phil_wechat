package com.phil.wechat.msg.model.event;

/**
 * 取消关注事件 
 * @author phil
 * @date  2017年6月30日
 *
 */
public class UnSubscribeEvent extends AbstractEvent {

	private static final long serialVersionUID = 5302504639088502221L;

	@Override
	public String setEvent() {
		return "unsubscribe";
	}
}
