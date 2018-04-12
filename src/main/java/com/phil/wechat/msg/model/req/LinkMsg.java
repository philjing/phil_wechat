package com.phil.wechat.msg.model.req;

/**
 * 链接消息
 * @author phil
 * @date  2017年6月30日
 *
 */
public class LinkMsg extends AbstractMsg {
	
	private static final long serialVersionUID = -5109736955133120376L;
	private String Title; // 消息标题
	private String Description; // 消息描述
	private String Url; // 消息链接

	@Override
	public String SetMsgType() {
		return "link";
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
