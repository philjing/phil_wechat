package com.phil.wechat.msg.model.resp;

import com.phil.wechat.msg.model.resp.bean.Video;

/**
 * 回复视频消息
 * 
 * @author phil
 * @data 2017年3月26日
 *
 */
public class RespVideoMsg extends RespAbstractMsg {
		
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

	@Override
	public String setMsgType() {
		return "video";
	}
	
}
