package com.phil.wechat.msg.model.req;

/**
 * 图片消息
 * @author phil
 * @date  2017年6月30日
 *
 */
public class ImageMsg extends AbstractMsg {

	private static final long serialVersionUID = 2945145292694690059L;
	private String PicUrl; // 图片链接
	private String MediaId; // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

	@Override
	public String SetMsgType() {
		return "image";
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
