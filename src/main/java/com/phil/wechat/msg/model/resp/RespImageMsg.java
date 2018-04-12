package com.phil.wechat.msg.model.resp;

import com.phil.wechat.msg.model.resp.bean.Image;

/**
 * 回复图片消息
 * @author phil
 * @data  2017年3月26日
 *
 */
public class RespImageMsg extends RespAbstractMsg {
	
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

	@Override
	public String setMsgType() {
		return "image";
	}
}
