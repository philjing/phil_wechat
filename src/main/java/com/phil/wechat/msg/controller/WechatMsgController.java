package com.phil.wechat.msg.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phil.modules.util.HttpReqUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.modules.util.MsgUtil;
import com.phil.modules.util.SignatureUtil;
import com.phil.modules.util.XmlUtil;
import com.phil.wechat.base.controller.BaseController;
import com.phil.wechat.base.result.WechatResult;
import com.phil.wechat.msg.constant.MsgConstant;
import com.phil.wechat.msg.model.media.UploadMediasResult;
import com.phil.wechat.msg.model.media.UploadNewsMedia;
import com.phil.wechat.msg.model.req.BasicMsg;
import com.phil.wechat.msg.model.resp.MassMsgResult;
import com.phil.wechat.msg.model.resp.RespAbstractMsg;
import com.phil.wechat.msg.model.resp.RespNewsMsg;
import com.phil.wechat.msg.model.template.req.WechatTemplateMsg;
import com.phil.wechat.msg.model.template.resp.TemplateMsgResult;
import com.phil.wechat.msg.service.WechatMsgService;

/**
 * @author phil
 * @date 2017年9月19日
 *
 */
@Controller
@RequestMapping("/wechat")
public class WechatMsgController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WechatMsgService wechatMsgService;

	/**
	 * 校验信息是否是从微信服务器发出，处理消息
	 * 
	 * @param request
	 * @param out
	 * @throws IOException
	 */
	@RequestMapping(value = "/handler", method = { RequestMethod.GET, RequestMethod.POST })
	public void processPost() throws Exception {
		this.getRequest().setCharacterEncoding("UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		boolean ispost = Objects.equals("POST", this.getRequest().getMethod().toUpperCase());
		if (ispost) {
			logger.debug("接入成功，正在处理逻辑");
			String respXml = defaultMsgDisPose(this.getRequest().getInputStream());// processRequest(request, response);
			if (StringUtils.isNotBlank(respXml)) {
				this.getResponse().getWriter().write(respXml);
			}
		} else {
			String signature = this.getRequest().getParameter("signature");
			// 时间戳
			String timestamp = this.getRequest().getParameter("timestamp");
			// 随机数
			String nonce = this.getRequest().getParameter("nonce");
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignatureUtil.checkSignature(signature, timestamp, nonce)) {
				// 随机字符串
				String echostr = this.getRequest().getParameter("echostr");
				logger.debug("接入成功，echostr {}", echostr);
				this.getResponse().getWriter().write(echostr);
			}
		}
	}

	/**
	 * 默认处理方法
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 * @throws DocumentException
	 */
	private String defaultMsgDisPose(InputStream inputStream) throws Exception {
		String result = null;
		if (inputStream != null) {
			Map<String, String> params = XmlUtil.parseStreamToMap(inputStream);
			if (params != null && params.size() > 0) {
				BasicMsg msgInfo = new BasicMsg();
				String createTime = params.get("CreateTime");
				String msgId = params.get("MsgId");
				msgInfo.setCreateTime(
						(createTime != null && !"".equals(createTime)) ? Integer.parseInt(createTime) : 0);
				msgInfo.setFromUserName(params.get("FromUserName"));
				msgInfo.setMsgId((msgId != null && !"".equals(msgId)) ? Long.parseLong(msgId) : 0);
				msgInfo.setToUserName(params.get("ToUserName"));
				WechatResult resultObj = coreHandler(msgInfo, params);
				if (resultObj == null) { //
					return null;
				}
				boolean success = resultObj.isSuccess(); // 如果 为true,则表示返回xml文件, 直接转换即可,否则按类型
				if (success) {
					result = resultObj.getObject().toString();
				} else {
					int type = resultObj.getType(); // 这里规定 1 图文消息 否则直接转换
					if (type == WechatResult.NEWSMSG) {
						RespNewsMsg newsMsg = (RespNewsMsg) resultObj.getObject();
						result = MsgUtil.newsMsgToXml(newsMsg);
					} else {
						RespAbstractMsg basicMsg = (RespAbstractMsg) resultObj.getObject();
						result = MsgUtil.msgToXml(basicMsg);
					}
				}
			} else {
				result = "msg is wrong";
			}
		}
		return result;
	}

	/**
	 * 核心处理
	 * 
	 * @param msg
	 *            消息基类
	 * @param params
	 *            xml 解析出来的 数据
	 * @return
	 */
	private WechatResult coreHandler(BasicMsg msg, Map<String, String> params) {
		WechatResult result = null;
		String msgType = params.get("MsgType");
		if (StringUtils.isEmpty(msgType)) {
			switch (msgType) {
			case MsgConstant.REQ_MESSAGE_TYPE_TEXT: // 文本消息
				result = wechatMsgService.textMsg(msg, params);
				break;
			case MsgConstant.REQ_MESSAGE_TYPE_IMAGE: // 图片消息
				result = wechatMsgService.imageMsg(msg, params);
				break;
			case MsgConstant.REQ_MESSAGE_TYPE_LINK: // 链接消息
				result = wechatMsgService.linkMsg(msg, params);
				break;
			case MsgConstant.REQ_MESSAGE_TYPE_LOCATION: // 地理位置
				result = wechatMsgService.locationMsg(msg, params);
				break;
			case MsgConstant.REQ_MESSAGE_TYPE_VOICE: // 音频消息
				result = wechatMsgService.voiceMsg(msg, params);
				break;
			case MsgConstant.REQ_MESSAGE_TYPE_SHORTVIDEO: // 短视频消息
				result = wechatMsgService.shortvideo(msg, params);
				break;
			case MsgConstant.REQ_MESSAGE_TYPE_VIDEO: // 视频消息
				result = wechatMsgService.videoMsg(msg, params);
				break;
			case MsgConstant.REQ_MESSAGE_TYPE_EVENT: // 事件消息
				String eventType = params.get("Event"); //
				if (eventType != null && !"".equals(eventType)) {
					switch (eventType) {
					case MsgConstant.EVENT_TYPE_SUBSCRIBE:
						result = wechatMsgService.subscribe(msg, params);
						break;
					case MsgConstant.EVENT_TYPE_UNSUBSCRIBE:
						result = wechatMsgService.unsubscribe(msg, params);
						break;
					case MsgConstant.EVENT_TYPE_SCAN:
						result = wechatMsgService.scan(msg, params);
						break;
					case MsgConstant.EVENT_TYPE_LOCATION:
						result = wechatMsgService.eventLocation(msg, params);
						break;
					case MsgConstant.EVENT_TYPE_CLICK:
						result = wechatMsgService.eventClick(msg, params);
						break;
					case MsgConstant.EVENT_TYPE_VIEW:
						result = wechatMsgService.eventView(msg, params);
						break;
					case MsgConstant.KF_CREATE_SESSION:
						result = wechatMsgService.kfCreateSession(msg, params);
						break;
					case MsgConstant.KF_CLOSE_SESSION:
						result = wechatMsgService.kfCloseSession(msg, params);
						break;
					case MsgConstant.KF_SWITCH_SESSION:
						result = wechatMsgService.kfSwitchSession(msg, params);
						break;
					default:
						wechatMsgService.eventDefaultReply(msg, params);
						break;
					}
				}
				break;
			default:
				wechatMsgService.defaultMsg(msg, params);
			}
		}
		return result;
	}

	/**
	 * 仅仅是个示例
	 * 
	 * @return
	 */
	public TemplateMsgResult sendTemplate() {
		TemplateMsgResult templateMsgResult = null;
		TreeMap<String, TreeMap<String, String>> params = new TreeMap<>();
		// 根据具体模板参数组装
		params.put("first", WechatTemplateMsg.item("您的户外旅行活动订单已经支付完成，可在我的个人中心中查看", "#000000"));
		params.put("keyword1", WechatTemplateMsg.item("8.1发现尼泊尔—人文与自然的旅行圣地", "#000000"));
		params.put("keyword2", WechatTemplateMsg.item("5000元", "#000000"));
		params.put("keyword3", WechatTemplateMsg.item("2017.1.2", "#000000"));
		params.put("keyword4", WechatTemplateMsg.item("5", "#000000"));
		params.put("remark", WechatTemplateMsg.item("请届时携带好身份证件准时到达集合地点，若临时退改将产生相应损失，敬请谅解,谢谢！", "#000000"));
		WechatTemplateMsg wechatTemplateMsg = new WechatTemplateMsg();
		wechatTemplateMsg.setTemplate_id("Ub2oYYFPf8ofmA17H31Zqu9Z_HLycZ7MC-Dx_Se1Nkw");
		wechatTemplateMsg.setTouser("241235134");
		wechatTemplateMsg.setUrl("http://music.163.com/#/song?id=27867140");
		wechatTemplateMsg.setData(params);
		String data = JsonUtil.toJsonString(wechatTemplateMsg);
		templateMsgResult = wechatMsgService.sendTemplate("accessToken", data);
		return templateMsgResult;
	}

	/** 以下 phil_token 请替换为自己公众号的token **/

	@RequestMapping("/mediaUploadimg")
	public String mediaUploadimg() throws Exception {
		// String mediaPath =
		// "http://localhost:8080/HiPhil_Phase1/img/534a75fe0af80021.jpg";
		String mediaPath = "C:/Users/phil/Pictures/image/8538572f61d7a94cf0b9fe0f290cdb28.jpg";
		// UploadMediasResult umr = HttpReqUtil.uploadTempMedia("phil_token", "image",
		// mediaPath);
		UploadMediasResult umr = HttpReqUtil.uploadTempMediaFile("phil_token", "image", mediaPath);
		logger.debug(" mediaUploadimg media_id result {} ", umr.getMedia_id());
		return null;
	}

	@RequestMapping("/sendByOpenid")
	public MassMsgResult sendByOpenid() throws Exception {
		// 根据OpenID列表群发图文消息
		String mediaPath1 = "C:/Users/phil/Pictures/image/8538572f61d7a94cf0b9fe0f290cdb28.jpg";
		UploadMediasResult result1 = HttpReqUtil.uploadTempMedia("phil_token", "image", mediaPath1);
		String mediaPath2 = "C:/Users/phil/Pictures/image/685977abgw1f8xqp46dgyj20qo0zktfi.jpg";
		UploadMediasResult result2 = HttpReqUtil.uploadTempMedia("phil_token", "image", mediaPath2);
		List<UploadNewsMedia> array = new ArrayList<>();
		UploadNewsMedia entity1 = new UploadNewsMedia();
		entity1.setAuthor("phil");
		entity1.setContent("人生只有经历才会懂得,只有懂得才知道珍惜，一生中，总会有一个人让你笑得最甜，也总会有一个人让你痛得最深，忘记,是善待自己");
		entity1.setContent_source_url("http://blog.csdn.net/phil_jing");
		// entity1.setDigest("digest");
		entity1.setShow_conver_pic(1);
		entity1.setThumb_media_id(result1.getMedia_id());
		entity1.setTitle("心灵鸡汤");
		array.add(entity1);
		UploadNewsMedia entity2 = new UploadNewsMedia();
		entity2.setAuthor("phil");
		entity2.setContent("什么是幸福，幸福就是自己的一种愉快的心理状态和感受。时时、事事都能使自己快乐的人才是最幸福的人。最快乐的人，就是最幸福的人。笑口常开的人，是最幸福的。");
		entity2.setContent_source_url("http://blog.csdn.net/phil_jing");
		// entity2.setDigest("digest");
		entity2.setShow_conver_pic(0);
		entity2.setThumb_media_id(result2.getMedia_id());
		entity2.setTitle(" 经典语录");
		array.add(entity2);
		// 获取图文消息结果
		UploadMediasResult ur = HttpReqUtil.uploadNewsMedia("phil_token", array);
		List<String> openids = new ArrayList<>();
		openids.add("ovHQ5v9-ZsHUcax_nTCQwiP-sBcg");
		openids.add("ovHQ5v6CW3INkWUsCl3olODif0cc");
		MassMsgResult result_news = wechatMsgService.sendMpnewsToOpenid("phil_token", openids, ur.getMedia_id());
		logger.debug(" send by openid msg {} ", result_news.getErrmsg());
		// 根据OpenID列表群发文字消息
		MassMsgResult result_text = wechatMsgService.sendTextToOpenid("phil_token", openids,
				"什么是幸福，幸福就是自己的一种愉快的心理状态和感受。时时、事事都能使自己快乐的人才是最幸福的人。最快乐的人，就是最幸福的人。笑口常开的人，是最幸福的");
		logger.debug(" send by openid msg {} ", result_text.getErrmsg());
		return null;
	}
}
