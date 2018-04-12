package com.phil.wechat.msg.model.req;

/**
 * 语音消息
 * 
 * @author phil
 * @date 2017年6月30日
 *
 */
public class VoiceMsg extends AbstractMsg {
	
	private static final long serialVersionUID = -943042955217963951L;
	private String MediaId; // 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String Format; // 语音格式，如amr，speex等
	private String Recognition;// 开通语音识别后才会出现

	@Override
	public String SetMsgType() {
		return "voice";
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
}
