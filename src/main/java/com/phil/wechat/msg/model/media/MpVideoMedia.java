package com.phil.wechat.msg.model.media;

/**
 * 视频post数据bean
 * 
 * @author phil
 * @date 2017年9月20日
 * 
 */
public class MpVideoMedia {

	private String media_id;
	private String title;
	private String description;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
