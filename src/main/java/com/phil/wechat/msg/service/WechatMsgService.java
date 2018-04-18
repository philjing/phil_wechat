package com.phil.wechat.msg.service;

import java.util.List;
import java.util.Map;

import com.phil.wechat.base.result.WechatResult;
import com.phil.wechat.msg.model.media.MpVideoMedia;
import com.phil.wechat.msg.model.req.BasicMsg;
import com.phil.wechat.msg.model.resp.MassMsgResult;
import com.phil.wechat.msg.model.template.resp.TemplateMsgResult;

/**
 * @author phil
 * @date  2017年9月20日
 *
 */
public interface WechatMsgService {
	
	/**
	 * 用户发送的为文本消息
	 * @param msg		基础消息
	 * @param params	请求参数 
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult textMsg(BasicMsg msg,Map<String,String> params);
	
	
	/**
	 * 链接消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult linkMsg(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 默认执行的消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult defaultMsg(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 音乐执行的消息
	 * @param msg	基础参数
	 * @param params	请求参数
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult musicMsg(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 图片消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult imageMsg(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 地理位置消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult locationMsg(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 语音消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult voiceMsg(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 视频消息
	 * @param msg 消息基类
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult videoMsg(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 小视频消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public WechatResult shortvideo(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 用户关注时调用的方法
	 * @param msg
	 * @param params
	 * @return
	 */
	public WechatResult subscribe(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 取消关注时调用的方法
	 * @param msg
	 * @param params
	 * @return
	 */
	public WechatResult unsubscribe(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 用户已关注时的事件推送
	 * @param msg	
	 * @param params
	 * @return
	 */
	public WechatResult scan(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 上报地理位置事件
	 * @param msg
	 * @param params
	 * @return
	 */
	public WechatResult eventLocation(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 点击菜单拉取消息时的事件推送 (自定义菜单的click在这里做响应)
	 * @param msg  
	 * @param params
	 * @return
	 */
	public WechatResult eventClick(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 点击菜单跳转链接时的事件推送 (自定义菜单的view在这里做响应)
	 * @param msg
	 * @param params
	 * @return
	 */
	public WechatResult eventView(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 事件类型默认返回
	 * @param msg
	 * @param params
	 * @return
	 */
	public WechatResult eventDefaultReply(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 客服创建回话 事件
	 * @param msg	 消息基类
	 * @param params  参数内容
	 * @return
	 */
	public WechatResult kfCreateSession(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 客服关闭回话事件
	 * @param msg	消息基类
	 * @param params	xml内容
	 * @return
	 */
	public WechatResult kfCloseSession(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 客服转接回话事件
	 * @param msg		消息基类
	 * @param params	xml内容
	 * @return
	 */
	public WechatResult kfSwitchSession(BasicMsg msg,Map<String,String> params);
	
	/**
	 * 发送模板消息
	 * @param accessToken
	 * @param data
	 * @return
	 */
	public TemplateMsgResult sendTemplate(String accessToken,String data);
	
	/**
	 * 根据标签进行群发文本消息
	 * @param accessToken  授权token
	 * @param entity  图文消息对象
	 * @return 
	 */;

	/**
	 * 根据标签进行群发图文消息
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendMpnewsToTag(String accessToken, int tagId, String mediaId);
	
	/**
	 * 根据标签进行群发图片
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendImageToTag(String accessToken, int tagId, String mediaId);
	
	/**
	 * 根据标签进行群发语音/音频
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendVoiceToTag(String accessToken, int tagId, String mediaId);
	
	/**
	 * 根据标签进行群发视频
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendVideoToTag(String accessToken, int tagId, String mediaId);
	
	/**
	 * 根据标签进行群发卡券
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param card_id
	 * @return 
	 */
	public MassMsgResult sendWxCardToTag(String accessToken, int tagId, String cardId);
	
	/**
	 * 根据OpenId进行群发图文消息
	 * @param accessToken  授权token
	 * @param tagId  标签
	 * @param mediaId uploadMedia方法获得
	 * @return 
	 */
	public MassMsgResult sendMpnewsToOpenid(String accessToken, List<String> openids, String mediaId);
	
	/**
	 * 根据OpenId进行群发文本消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param content
	 * @return 
	 */
	public MassMsgResult sendTextToOpenid(String accessToken, List<String> openids, String content);
	
	/**
	 * 根据OpenId进行群发语音消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param mediaId
	 * @return 
	 */
	public MassMsgResult sendVocieToOpenid(String accessToken, List<String> openids, String mediaId);

	/**
	 * 根据OpenId进行群发图片消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param mediaId
	 * @return 
	 */
	public MassMsgResult sendImageToOpenid(String accessToken, List<String> openids, String mediaId);
	
	/**
	 * 根据OpenId进行群发视频消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param mpVideoMedia uploadMediaVideo方法获得media
	 * @return 
	 */
	public MassMsgResult sendVideoToOpenid(String accessToken, List<String> openids, MpVideoMedia mpVideoMedia);
	
	/**
	 * 根据OpenId进行群发卡券消息
	 * @param accessToken  授权token
	 * @param openids  openid
	 * @param mediaId
	 * @return 
	 */
	public MassMsgResult sendWxcardToOpenid(String accessToken, List<String> openids, String cardId);

}
