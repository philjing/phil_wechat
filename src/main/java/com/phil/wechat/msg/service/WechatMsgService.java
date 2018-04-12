package com.phil.wechat.msg.service;

import java.util.List;
import java.util.Map;

import com.phil.wechat.base.result.WechatResult;
import com.phil.wechat.msg.model.media.UploadNews;
import com.phil.wechat.msg.model.req.BasicMsg;
import com.phil.wechat.msg.model.resp.MsgResult;
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
	 * 上传图文消息的素材
	 * 
	 * @param accessToken  授权token
	 * @param path 路径
	 * @param entity  图文消息对象
	 * @return   result.isSuccess 则返回成功信息,可强制转换为UploadNews 对象
	 */
	public WechatResult uploadnews(String accessToken, String path, List<UploadNews> entity);
	
	
	/**
	 * 通过openid 群发 图文消息
	 * @param list		待接收消息的openid 集合
	 * @param mediaId	图文消息的id
	 * @return 			MsgResult 对象
	 */
	public MsgResult sendByOpenIdOfNews(String accessToken,List<String> list,String mediaId,String url);
	
	/**
	 * 通过 openid 群发文字消息
	 * @param accessToken	授权token
	 * @param list			待接口消息的openId 集合
	 * @param content		需要发送的文本内容
	 * @return    MsgResult
	 */
	public MsgResult sendByOpenIdOfText(String accessToken,List<String> list,String url,String content);

}
