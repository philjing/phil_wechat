package com.phil.modules.config;

/**
 * 微信公众号相关的配置
 * 
 * @author phil
 * @date 2017年7月2日
 *
 */
public class WechatConfig {

	public static final String APP_ID = "xx";
	public static final String APP_SECRET = "xx";
	public static final String WECHAT_TOKEN = "xx";

	public static final String MCH_ID = "xx";// 商户号
	public static final String API_KEY = "xx";// API密钥

	// 异步通知URL
	public static final String NOTIFY_URL = "自己的异步通知URL";

	// 微信支付统一接口(POST)
	public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信退款接口(POST)
	public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 订单查询接口(POST)
	public static final String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	// 关闭订单接口(POST)
	public static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	// 退款查询接口(POST)
	public static final String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	// 对账单接口(POST)
	public static final String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	// 短链接转换接口(POST)
	public static final String PAY_SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	// 接口调用上报接口(POST)
	public static final String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
	// Token
	public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

	// 临时二维码
	public static final String QR_SCENE = "QR_SCENE"; // -1
	// 永久二维码
	public static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";// 0
	// 永久二维码(字符串)
	public static final String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";// 1
	// 创建二维码
	public static final String CREATE_TICKET_PATH = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	// 通过ticket换取二维码
	public static final String SHOW_QRCODE_PATH = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	// 长链接转成短链接
	public static final String WECHAT_SHORT_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/shorturl";

	public static final String LONG2SHORT = "long2short";

	// 创建菜单
	public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";
	// 查询自定义菜单
	public static final String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";
	// 删除自定义菜单
	public static final String MENU_DELTE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";

	// 发送模板消息
	public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send";

	// 授权链接
	public static final String AUTHORIZE_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	// 获取token的链接
	public static final String GET_OAUTH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	// 刷新token
	public static final String REFRESH_OAUTH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	// 获取授权用户信息
	public static final String SNS_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
	// 判断用户accessToken是否有效
	public static final String CHECK_SNS_AUTH_STATUS_URL = "https://api.weixin.qq.com/sns/auth";
	// 授权登陆链接
	public static final String QR_CONNECT_URL = "https://open.weixin.qq.com/connect/qrconnect";
	// 获取jsapi_ticket
	public static final String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";// ?access_token=ACCESS_TOKEN&type=jsapi

	// 上传图文消息内的图片获取URL
	public static final String UPLOAD_IMG_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
	// 上传图文消息素材的path
	public static final String UPLOAD_NEWS_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
	// 按分组进行群发
	public static final String SEND_ALL_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";
	// 按照openid进行群发消息
	public static final String SEND_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send";
	// 删除群发消息
	public static final String DELETE_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/delete";
	// 预览接口
	public static final String PREVIEW_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
	// 查询群发消息的发送状态
	public static final String GET_MASS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/get";

	// 多媒体上传
	public static final String UPLOAD_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload"; // ?access_token=ACCESS_TOKEN&type=TYPE
	public static final String UPLOAD_FOREVER_NEWS_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news";
	public static final String UPLOAD_TEMP_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload";
	public static final String UPLOAD_FOREVER_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：视频
	 */
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

	/**
	 * 请求消息类型: 短视频消息
	 */
	public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	/**
	 * 事件类型: view(自定义菜单view事件)
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";

	/**
	 * 事件类型:scan(用户已关注时的事件推送)
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";

	/**
	 * 事件类型:LOCATION(上报地理位置事件)
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";

	/**
	 * 创建回话(接入回话) 事件： kf_create_session
	 */
	public static final String KF_CREATE_SESSION = "kf_create_session";

	/**
	 * 关闭回话事件: kf_close_session
	 */
	public static final String KF_CLOSE_SESSION = "kf_close_session";

	/**
	 * 转接回话事件: kf_switch_session
	 */
	public static final String KF_SWITCH_SESSION = "kf_switch_session";

}
