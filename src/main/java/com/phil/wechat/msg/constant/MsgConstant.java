package com.phil.wechat.msg.constant;

public class MsgConstant {

	//请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	//请求消息类型：图片
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	//请求消息类型：链接
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	//请求消息类型：地理位置
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	//请求消息类型：音频
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	//请求消息类型：视频
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

	//请求消息类型: 短视频消息
	public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

	//请求消息类型：推送
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	//事件类型：subscribe(订阅)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	//事件类型：unsubscribe(取消订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	//事件类型：CLICK(自定义菜单点击事件)
	public static final String EVENT_TYPE_CLICK = "CLICK";

	//事件类型: view(自定义菜单view事件)
	public static final String EVENT_TYPE_VIEW = "VIEW";

	//事件类型:scan(用户已关注时的事件推送)
	public static final String EVENT_TYPE_SCAN = "SCAN";

	//事件类型:LOCATION(上报地理位置事件)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";

	//创建回话(接入回话) 事件： kf_create_session
	public static final String KF_CREATE_SESSION = "kf_create_session";

	//关闭回话事件: kf_close_session
	public static final String KF_CLOSE_SESSION = "kf_close_session";

	//转接回话事件: kf_switch_session
	public static final String KF_SWITCH_SESSION = "kf_switch_session";
}
