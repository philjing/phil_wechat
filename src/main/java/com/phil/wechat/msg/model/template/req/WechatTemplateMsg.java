package com.phil.wechat.msg.model.template.req;

import java.util.TreeMap;

import com.phil.modules.annotation.NotRequire;

/**
 * 模板消息
 * @author phil
 * @date 2017年7月2日
 *
 */
public class WechatTemplateMsg {

	private String touser; //接收者openid

	private String template_id; //模板ID

	@NotRequire//只是个标识
	private String url; //模板跳转链接

	// "miniprogram":{ 未加入
	// "appid":"xiaochengxuappid12345",
	// "pagepath":"index?foo=bar"
	// },

	private TreeMap<String, TreeMap<String, String>> data; //data数据

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TreeMap<String, TreeMap<String, String>> getData() {
		return data;
	}

	public void setData(TreeMap<String, TreeMap<String, String>> data) {
		this.data = data;
	}

	/**
	 * 参数
	 * @param value
	 * @param color 可不填
	 * @return
	 */
	public static TreeMap<String, String> item(String value, String color) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("value", value);
		params.put("color", color);
		return params;
	}
}
