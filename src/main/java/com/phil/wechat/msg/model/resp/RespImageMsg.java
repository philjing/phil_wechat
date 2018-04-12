package com.phil.wechat.msg.model.resp;

/**
 * 回复图片消息
 * 
 * @author phil
 * @data 2017年3月26日
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

	/**
	 * 
	 * @author phil
	 * @date 2017年7月19日
	 *
	 */
	public class Image {

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
