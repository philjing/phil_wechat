package com.phil.wechat.msg.model.resp.bean;

/**
 * 
 * @author phil
 * @date  2017年7月19日
 *
 */
public class Video {
	
	//通过素材管理中的接口上传多媒体文件，得到的id。
	private String MediaId;
	//视频消息的标题
	private String Title;
	//视频消息的描述
	private String Description;
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
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
}
