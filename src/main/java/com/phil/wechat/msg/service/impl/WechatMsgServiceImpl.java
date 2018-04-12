package com.phil.wechat.msg.service.impl;

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
import com.phil.wechat.msg.model.media.UploadNews;
import com.phil.wechat.msg.model.req.BasicMsg;
import com.phil.wechat.msg.model.resp.MsgResult;
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
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		String result = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, WechatConfig.SEND_TEMPLATE_MESSAGE,
				params, data);
		templateMsgResult = JsonUtil.fromJsonString(result, TemplateMsgResult.class);
		//log.....
		return templateMsgResult;
	}

	public WechatResult uploadnews(String accessToken, String url, List<UploadNews> entity) {
		WechatResult wechatResult = null;
		// url 的参数
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		// post 提交的参数
		TreeMap<String, List<UploadNews>> dataParams = new TreeMap<String, List<UploadNews>>();
		dataParams.put("articles", entity);
		String data = JsonUtil.toJsonString(dataParams);
		String result = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, url, params, data);
		wechatResult = JsonUtil.fromJsonString(result, WechatResult.class); // 转换数据
		return wechatResult;
	}

	/**
	 * 通过openid 群发 图文消息
	 * 
	 * @param list
	 *            待接收消息的openid 集合
	 * @param mediaId
	 *            图文消息的id
	 * @return MsgResult 对象
	 */
	public MsgResult sendByOpenIdOfNews(String accessToken, List<String> list, String url, String mediaId) {
		MsgResult result = null;
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("touser", list);
		// list Params
		TreeMap<String, String> medaidParams = new TreeMap<String, String>();
		medaidParams.put("media_id", mediaId);
		params.put("mpnews", medaidParams);
		params.put("msgtype", "mpnews");
		String data = JsonUtil.toJsonString(params);
		result = sendByOpenid(accessToken, url, data);
		return result;
	}

	/**
	 * 通过 openid 群发文字消息
	 * 
	 * @param accessToken
	 *            授权token
	 * @param list
	 *            待接口消息的openId 集合
	 * @param content
	 *            需要发送的文本内容
	 * @return MsgResult
	 */
	public MsgResult sendByOpenIdOfText(String accessToken, List<String> list, String url, String content) {
		MsgResult result = null;
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("touser", list);
		// content Params
		TreeMap<String, String> contentParams = new TreeMap<String, String>();
		contentParams.put("content", content);
		//
		params.put("text", contentParams);
		params.put("msgtype", "text");
		String data = JsonUtil.toJsonString(params);
		result = sendByOpenid(accessToken, url, data);
		return result;
	}

	/**
	 * 通过openid列表进行群发
	 * 
	 * @param accessToken
	 * @param data
	 * @return
	 */
	private MsgResult sendByOpenid(String accessToken, String url, String data) {
		MsgResult msgResult = null;
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("access_token", accessToken);
		String result = HttpReqUtil.HttpsDefaultExecute(SystemConfig.POST_METHOD, url, params, data);
		msgResult = JsonUtil.fromJsonString(result, MsgResult.class);
		return msgResult;
	}

}
