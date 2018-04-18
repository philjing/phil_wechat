package com.phil.wechat.msg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phil.modules.config.SystemConfig;
import com.phil.modules.config.WechatConfig;
import com.phil.modules.util.DateTimeUtil;
import com.phil.modules.util.HttpReqUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.base.result.WechatResult;
import com.phil.wechat.msg.model.media.MpVideoMedia;
import com.phil.wechat.msg.model.media.UploadMediasResult;
import com.phil.wechat.msg.model.req.BasicMsg;
import com.phil.wechat.msg.model.resp.MassMsgResult;
import com.phil.wechat.msg.model.resp.RespTextMsg;
import com.phil.wechat.msg.model.template.resp.TemplateMsgResult;
import com.phil.wechat.msg.service.WechatMsgService;

/**
 * @author phil
 * @date 2017年9月20日
 *
 */
@Service
public class WechatMsgServiceImpl implements WechatMsgService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// @Autowired
	// private GenericDao<WechatUser, Serializable> wechatUserDao;

	// @Autowired 
	// private WechatUserMapper wechatUserMapper;

	/**
	 * 处理用户发送的为文本消息
	 * 
	 * @param msg 基础消息
	 * @param params  请求参数
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult textMsg(BasicMsg msg, Map<String, String> params) {
		WechatResult result = new WechatResult();
		RespTextMsg text = new RespTextMsg();
		text.setContent(params.get("Content").trim());// 自动回复
		text.setCreateTime(DateTimeUtil.currentTime());
		text.setToUserName(msg.getFromUserName());
		text.setFromUserName(msg.getToUserName());
		result.setObject(text);
		return result;
	}

	/**
	 * 链接消息
	 * 
	 * @param msg
	 * @param params
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult linkMsg(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 默认执行的消息
	 * 
	 * @param msg
	 * @param params
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult defaultMsg(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 音乐执行的消息
	 * 
	 * @param msg
	 *            基础参数
	 * @param params
	 *            请求参数
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult musicMsg(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 图片消息
	 * 
	 * @param msg
	 * @param params
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult imageMsg(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 地理位置消息
	 * 
	 * @param msg
	 * @param params
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult locationMsg(BasicMsg msg, Map<String, String> params) {
		String label = params.get("Label");
		logger.debug("地理位置信息" + label);
		// String url =
		// "http://openapi.aibang.com/bus/stats?app_key=f41c8afccc586de03a99c86097e98ccb";
		// TreeMap<String,String> map = new TreeMap<String,String>();
		// map.put(city, value)
		// map.put("map", HttpReqUtil.urlEncode(label,
		// SystemConfig.CHARACTER_ENCODING));
		// String xml = HttpReqUtil.HttpDefaultExecute(HttpReqUtil.GET_METHOD, url, map,
		// "");
		WechatResult result = new WechatResult();
		RespTextMsg text = new RespTextMsg();
		// try {
		// RespBusStats respBusStats = XmlUtil.getObjectFromXML(xml,
		// RespBusStats.class);
		// text.setContent("一共查出"+respBusStats.getResult_num()+"条线路");//自动回复
		// } catch (Exception e) {
		// text.setContent("未能查询到");
		// }
		text.setCreateTime(DateTimeUtil.currentTime());
		text.setToUserName(msg.getFromUserName());
		text.setFromUserName(msg.getToUserName());
		result.setObject(text);
		return result;
	}

	/**
	 * 语音消息
	 * 
	 * @param msg
	 * @param params
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult voiceMsg(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 视频消息
	 * 
	 * @param msg
	 *            消息基类
	 * @param params
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult videoMsg(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 小视频消息
	 * 
	 * @param msg
	 * @param params
	 * @return 返回需要该消息回复的xml格式类型的字符串
	 */
	@Override
	public WechatResult shortvideo(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 用户关注时调用的方法 用户未关注时进行关注后的事件推送/关注事件
	 * 
	 * @param msg
	 * @param params
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public WechatResult subscribe(BasicMsg msg, Map<String, String> params) {
		WechatResult result = new WechatResult();
		// String content = "欢迎关注我的个人博客" + "<a
		// href=\"http://blog.csdn.net/phil_jing\">CSDN</a>"
		// + "<a href=\"http://www.cnblogs.com/phil_jing\">博客园</a>";
		// RespTextMsg text = new RespTextMsg();
		// text.setContent(content);
		// text.setCreateTime(DateTimeUtil.currentTime());
		// text.setToUserName(msg.getFromUserName());
		// text.setFromUserName(msg.getToUserName());
		// result.setObject(text);
		// logger.debug(DateTimeUtil.formatDate(text.getCreateTime(),
		// DateTimeUtil.YMDHMS_DATEFORMA) + "关注的openid："
		// + msg.getFromUserName());
		// // 保存
		// /**** EventKey,Ticket处理 不为空说明有参数 ****/
		// if (params.get("EventKey") != null) {
		// logger.debug("二维码" + params.get("EventKey"));
		// /**** 逻辑处理 ****/
		//
		// }
		// WechatUser user = wechatUserMapper.findByOpenid(msg.getFromUserName());
		// if (user == null) {
		// user = new WechatUser();
		// user.setId(UUIDGenerator.generate().toString());
		// user.setOpenid(msg.getFromUserName());
		// user.setCreateUser(msg.getFromUserName());
		// user.setSubscribe(1);
		// user.setCreateTime(text.getCreateTime());
		// wechatUserMapper.saveEntity(user);
		// } else {
		// user.setSubscribe(1); // 以前关注过的
		// user.setUpdateTime(text.getCreateTime());
		// user.setUpdateUser(msg.getFromUserName());
		// wechatUserMapper.updateEntity(user);
		// }
		return result;
	}

	/**
	 * 取消关注时调用的方法
	 * 
	 * @param msg
	 * @param params
	 * @return
	 */
	@Override
	public WechatResult unsubscribe(BasicMsg msg, Map<String, String> params) {
		// WechatUser user = wechatUserMapper.findByOpenid(msg.getFromUserName());
		// if (user != null) {
		// user.setSubscribe(0);
		// user.setUpdateTime(DateTimeUtil.currentTime());
		// user.setUpdateUser(msg.getFromUserName());
		// wechatUserMapper.updateEntity(user);
		// }
		return null;
	}

	/**
	 * 用户已关注时的事件推送
	 * 
	 * @param msg
	 * @param params
	 * @return
	 */
	@Override
	public WechatResult scan(BasicMsg msg, Map<String, String> params) {
		logger.debug("已关注事件二维码参数{}", params.get("EventKey"));
		/**** 逻辑处理 ****/
		return null;
	}

	/**
	 * 上报地理位置事件
	 * 
	 * @param msg
	 * @param params
	 * @return
	 */
	@Override
	public WechatResult eventLocation(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 点击菜单拉取消息时的事件推送 (自定义菜单的click在这里做响应)
	 * 
	 * @param msg
	 * @param params
	 * @return
	 */
	@Override
	public WechatResult eventClick(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 点击菜单跳转链接时的事件推送 (自定义菜单的view在这里做响应)
	 * 
	 * @param msg
	 * @param params
	 * @return
	 */
	@Override
	public WechatResult eventView(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 事件类型默认返回
	 * 
	 * @param msg
	 * @param params
	 * @return
	 */
	@Override
	public WechatResult eventDefaultReply(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 客服创建回话 事件
	 * 
	 * @param msg
	 *            消息基类
	 * @param params
	 *            参数内容
	 * @return
	 */
	@Override
	public WechatResult kfCreateSession(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 客服关闭回话事件
	 * 
	 * @param msg
	 *            消息基类
	 * @param params
	 *            xml内容
	 * @return
	 */
	@Override
	public WechatResult kfCloseSession(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 客服转接回话事件
	 * 
	 * @param msg
	 *            消息基类
	 * @param params
	 *            xml内容
	 * @return
	 */
	@Override
	public WechatResult kfSwitchSession(BasicMsg msg, Map<String, String> params) {

		return null;
	}

	/**
	 * 发送模板消息
	 * 
	 * @param accessToken
	 * @param data
	 * @return 状态
	 */
	@Override
	public TemplateMsgResult sendTemplate(String accessToken, String data) {
		TemplateMsgResult templateMsgResult = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		String result = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_TEMPLATE_MESSAGE,
				params, data);
		templateMsgResult = JsonUtil.fromJsonString(result, TemplateMsgResult.class);
		//log.....
		return templateMsgResult;
	}

	/**
	 * 根据标签进行群发文本消息
	 * @param accessToken  授权token
	 * @param entity  图文消息对象
	 * @return 
	 */
	public MassMsgResult sendTextToTag(String accessToken, int tagId, String content){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> filterParams = new HashMap<>();
		filterParams.put("is_to_all", false);
		filterParams.put("tag_id", tagId);
		Map<String, Object> textParams = new HashMap<>();
		textParams.put("content", content);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("filter", filterParams);
		dataParams.put("text", textParams);
		dataParams.put("msgtype", "text");
		String data = JsonUtil.toJsonString(dataParams);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_ALL_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据标签进行群发图文消息
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendMpnewsToTag(String accessToken, int tagId, String mediaId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> filterParams = new HashMap<>();
		filterParams.put("is_to_all", false);
		filterParams.put("tag_id", tagId);
		Map<String, Object> mpnewsParams = new HashMap<>();
		mpnewsParams.put("media_id", mediaId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("filter", filterParams);
		dataParams.put("mpnews", mpnewsParams);
		dataParams.put("msgtype", "mpnews");
		dataParams.put("send_ignore_reprint", 0);//不能省略
		String data = JsonUtil.toJsonString(dataParams);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_ALL_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据标签进行群发图片
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendImageToTag(String accessToken, int tagId, String mediaId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> filterParams = new HashMap<>();
		filterParams.put("is_to_all", false);
		filterParams.put("tag_id", tagId);
		Map<String, Object> imageParams = new HashMap<>();
		imageParams.put("media_id", mediaId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("filter", filterParams);
		dataParams.put("image", imageParams);
		dataParams.put("msgtype", "image");
		String data = JsonUtil.toJsonString(dataParams);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_ALL_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据标签进行群发语音/音频
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendVoiceToTag(String accessToken, int tagId, String mediaId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> filterParams = new HashMap<>();
		filterParams.put("is_to_all", false);
		filterParams.put("tag_id", tagId);
		Map<String, Object> voiceParams = new HashMap<>();
		voiceParams.put("media_id", mediaId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("filter", filterParams);
		dataParams.put("voice", voiceParams);
		dataParams.put("msgtype", "voice");
		String data = JsonUtil.toJsonString(dataParams);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_ALL_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据标签进行群发视频
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendVideoToTag(String accessToken, int tagId, String mediaId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> filterParams = new HashMap<>();
		filterParams.put("is_to_all", false);
		filterParams.put("tag_id", tagId);
		Map<String, Object> mpvideoParams = new HashMap<>();
		mpvideoParams.put("media_id", mediaId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("filter", filterParams);
		dataParams.put("mpvideo", mpvideoParams);
		dataParams.put("msgtype", "mpvideo");
		String data = JsonUtil.toJsonString(dataParams);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_ALL_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据标签进行群发卡券
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param card_id
	 * @return 
	 */
	public MassMsgResult sendWxCardToTag(String accessToken, int tagId, String cardId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> filterParams = new HashMap<>();
		filterParams.put("is_to_all", false);
		filterParams.put("tag_id", tagId);
		Map<String, Object> wxcardParams = new HashMap<>();
		wxcardParams.put("card_id", cardId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("filter", filterParams);
		dataParams.put("wxcard", wxcardParams);
		dataParams.put("msgtype", "wxcard");
		String data = JsonUtil.toJsonString(dataParams);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_ALL_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据OpenId进行群发图文消息
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendMpnewsToOpenid(String accessToken, List<String> openids, String mediaId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> mpnewsParams = new HashMap<>();
		mpnewsParams.put("media_id", mediaId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("touser", openids);
		dataParams.put("mpnews", mpnewsParams);
		dataParams.put("msgtype", "mpnews");
		dataParams.put("send_ignore_reprint", 0);
		String data = JsonUtil.toJsonString(dataParams);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据OpenId进行群发文本消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param content
	 * @return 
	 */
	public MassMsgResult sendTextToOpenid(String accessToken, List<String> openids, String content){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> textParams = new HashMap<>();
		textParams.put("content", content);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("touser", openids);
		dataParams.put("text", textParams);
		dataParams.put("msgtype", "text");
		String data = JsonUtil.toJsonString(dataParams);
		System.out.println(data);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据OpenId进行群发语音消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param mediaId
	 * @return 
	 */
	public MassMsgResult sendVocieToOpenid(String accessToken, List<String> openids, String mediaId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> voiceParams = new HashMap<>();
		voiceParams.put("media_id", mediaId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("touser", openids);
		dataParams.put("voice", voiceParams);
		dataParams.put("msgtype", "voice");
		String data = JsonUtil.toJsonString(dataParams);
		System.out.println(data);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 根据OpenId进行群发图片消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param mediaId
	 * @return 
	 */
	public MassMsgResult sendImageToOpenid(String accessToken, List<String> openids, String mediaId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> imageParams = new HashMap<>();
		imageParams.put("media_id", mediaId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("touser", openids);
		dataParams.put("image", imageParams);
		dataParams.put("msgtype", "image");
		String data = JsonUtil.toJsonString(dataParams);
		System.out.println(data);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据OpenId进行群发视频消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param mpVideoMedia uploadMediaVideo方法获得media
	 * @return 
	 */
	public MassMsgResult sendVideoToOpenid(String accessToken, List<String> openids, MpVideoMedia mpVideoMedia){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("touser", openids);
		dataParams.put("mpvideo", mpVideoMedia);
		dataParams.put("msgtype", "mpvideo");
		String data = JsonUtil.toJsonString(dataParams);
		System.out.println(data);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据OpenId进行群发卡券消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param mediaId
	 * @return 
	 */
	public MassMsgResult sendWxcardToOpenid(String accessToken, List<String> openids, String cardId){
		MassMsgResult result = null;
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// post 提交的参数
		Map<String, Object> wxcardParams = new HashMap<>();
		wxcardParams.put("card_id", cardId);
		TreeMap<String,Object> dataParams = new TreeMap<>();
		dataParams.put("touser", openids);
		dataParams.put("wxcard", wxcardParams);
		dataParams.put("msgtype", "wxcard");
		String data = JsonUtil.toJsonString(dataParams);
		String json = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_MASS_MESSAGE_URL, params, data);
		try {
			result = JsonUtil.fromJsonString(json, MassMsgResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args){
		List<String> openids = new ArrayList<String>();
		openids.add("o2z6G1d-okcgWS6t4lJsNxIIOjPg");
		openids.add("o2z6G1UE-sMc5_kdV8pKuZkqDggk");
		String accessToken = "改成你自己的";
		String mediaPath = "C:/Users/phil/Desktop/9d0792622bf467e0c0c718c648742814.mp4";
		UploadMediasResult mediaResult = HttpReqUtil.uploadTempMediaFile(accessToken,"video",mediaPath);
		System.out.println(mediaResult.getMedia_id()); //上传下载多媒体文件得到MediaId
		MpVideoMedia m = new MpVideoMedia();
		m.setMedia_id(mediaResult.getMedia_id());
		m.setTitle("这是一个标题");
		m.setDescription("这是一个描述");
		UploadMediasResult result = HttpReqUtil.uploadMediaVideo(accessToken,m);
		m.setMedia_id(result.getMedia_id()); //视频上传接口获取到MediaId
		System.out.println(result.getMedia_id());
		new WechatMsgServiceImpl().sendVideoToOpenid(accessToken,openids,m);
	}
}
