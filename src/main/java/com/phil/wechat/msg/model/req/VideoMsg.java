package com.phil.wechat.msg.model.req;

/**
 * 语音消息
 * @author phil
 * @date  2017年6月30日
 *
 */
public class VideoMsg extends AbstractMsg {
	
	private static final long serialVersionUID = -5573628906037988358L;
	private String MediaId; // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId; // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据

	@Override
	public String SetMsgType() {
		return "video";
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
