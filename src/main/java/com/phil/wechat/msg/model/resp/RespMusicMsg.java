package com.phil.wechat.msg.model.resp;

import com.phil.wechat.msg.model.resp.bean.Music;

/**
 * 音乐消息
 * @author phil
 *
 */
public class RespMusicMsg extends RespAbstractMsg {
	
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	@Override
	public String setMsgType() {
		// TODO Auto-generated method stub
		return "music";
	}
}
