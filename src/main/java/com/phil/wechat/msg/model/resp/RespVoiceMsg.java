package com.phil.wechat.msg.model.resp;

/**
 * 回复语音消息
 * 
 * @author phil
 * @data 2017年3月26日
 *
 */
public class RespVoiceMsg extends RespAbstractMsg {

	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

	@Override
	public String setMsgType() {
		return "voice";
	}

	/**
	 * 
	 * @author phil
	 * @data 2017年6月30日
	 *
	 */
	public class Voice {

		// 通过素材管理中的接口上传多媒体文件，得到的id。
		private String MediaId;

		public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
	}
}
