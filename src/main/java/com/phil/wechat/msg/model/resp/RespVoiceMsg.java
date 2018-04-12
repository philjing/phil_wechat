package com.phil.wechat.msg.model.resp;

import com.phil.wechat.msg.model.resp.bean.Voice;

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
}
