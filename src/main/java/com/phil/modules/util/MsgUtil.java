package com.phil.modules.util;

import com.phil.modules.constant.SystemConstant;
import com.phil.modules.util.XmlUtil.XStreamFactroy;
import com.phil.wechat.msg.model.resp.RespAbstractMsg;
import com.phil.wechat.msg.model.resp.RespImageMsg;
import com.phil.wechat.msg.model.resp.RespImageMsg.Image;
import com.phil.wechat.msg.model.resp.RespMusicMsg;
import com.phil.wechat.msg.model.resp.RespMusicMsg.Music;
import com.phil.wechat.msg.model.resp.RespNewsMsg;
import com.phil.wechat.msg.model.resp.RespNewsMsg.Articles;
import com.phil.wechat.msg.model.resp.RespNewsMsg.Articles.Item;
import com.phil.wechat.msg.model.resp.RespTextMsg;
import com.phil.wechat.msg.model.resp.RespVideoMsg;
import com.phil.wechat.msg.model.resp.RespVideoMsg.Video;
import com.phil.wechat.msg.model.resp.RespVoiceMsg;
import com.phil.wechat.msg.model.resp.RespVoiceMsg.Voice;
import com.phil.wechat.pay.constant.PayConstant;
import com.phil.wechat.pay.model.rep.AbstractPayParams;
import com.thoughtworks.xstream.XStream;

/**
 * 默认请求消息处理类
 * 
 * @author phil
 * 
 */
public class MsgUtil {

	/**
	 * 将java对象转换为xml
	 * 
	 * @param msg
	 * @return
	 */
	public static String msgToXml(RespAbstractMsg msg) {
		String result = "";
		if (msg != null) {
			XStream xs = XStreamFactroy.init(true);
			xs.alias("xml", msg.getClass());
			result = xs.toXML(msg);
		}
		return result;
	}

	/**
	 * 文本消息
	 * 
	 * @param text
	 * @return
	 */
	public static String textMsgToXml(RespTextMsg text) {
		String result = "";
		if (text != null) {
			XStream xs = XStreamFactroy.init(true);
			xs.alias("xml", RespTextMsg.class);
			result = xs.toXML(text);
		}
		return result;
	}

	/**
	 * 图片消息
	 * 
	 * @param image
	 * @return
	 */
	public static String imageMsgToXml(RespImageMsg image) {
		String result = "";
		if (image != null) {
			XStream xs = XStreamFactroy.init(true);
			xs.alias("xml", RespImageMsg.class);
			xs.aliasField("Image", Image.class, "image");
			result = xs.toXML(image);
		}
		return result;
	}

	/**
	 * 音乐消息
	 * 
	 * @param music
	 * @return
	 */
	public static String musicMsgToXml(RespMusicMsg music) {
		String result = "";
		if (music != null) {
			XStream xs = XStreamFactroy.init(true);
			xs.alias("xml", RespMusicMsg.class);
			xs.aliasField("Music", Music.class, "music");
			result = xs.toXML(music);
		}
		return result;
	}

	/**
	 * 图文消息
	 * 
	 * @param news
	 * @return
	 */
	public static String newsMsgToXml(RespNewsMsg news) {
		String result = "";
		if (news != null) {
			XStream xs = XStreamFactroy.init(true);
			xs.alias("xml", RespNewsMsg.class);
			xs.addImplicitCollection(Articles.class, "list", "item", Item.class);
			xs.aliasField("Articles", RespNewsMsg.class, "articles");
			result = xs.toXML(news);
		}
		return result;
	}

	/**
	 * 视频消息
	 * 
	 * @param news
	 * @return
	 */
	public static String videoMsgToXml(RespVideoMsg video) {
		String result = "";
		if (video != null) {
			XStream xs = XStreamFactroy.init(true);
			xs.alias("xml", RespVideoMsg.class);
			xs.aliasField("Video", Video.class, "video");
			result = xs.toXML(video);
		}
		return result;
	}

	/**
	 * 语音消息
	 * 
	 * @param voice
	 * @return
	 */
	public static String voiceMsgToXml(RespVoiceMsg voice) {
		String result = "";
		if (voice != null) {
			XStream xs = XStreamFactroy.init(true);
			xs.alias("xml", RespVoiceMsg.class);
			xs.aliasField("Voice", Voice.class, "voice");
			result = xs.toXML(voice);
		}
		return result;
	}

	/**
	 * 支付参数
	 * @param params
	 * @return
	 */
	public static String abstractPayToXml(AbstractPayParams params) {
		String sign = SignatureUtil.createSign(params, PayConstant.API_KEY, SystemConstant.DEFAULT_CHARACTER_ENCODING);
		params.setSign(sign);
		return XmlUtil.toSplitXml(params);
	}
}