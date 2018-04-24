package com.phil.wechat.qrcode.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonSyntaxException;
import com.phil.modules.config.WechatConfig;
import com.phil.modules.constant.SystemConstant;
import com.phil.modules.util.HttpReqUtil;
import com.phil.modules.util.IOUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.base.result.WechatResult;
import com.phil.wechat.qrcode.constant.QRCodeConstant;
import com.phil.wechat.qrcode.model.WechatQRCode;
import com.phil.wechat.qrcode.model.WechatQRCodeShortUrl;
import com.phil.wechat.qrcode.service.WechatQRCodeService;

@Service
public class WechatQRCodeServiceImpl implements WechatQRCodeService{

	/**
	 * 创建临时带参数二维码
	 * 
	 * @param accessToken
	 * @expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * @param sceneId 场景Id
	 * @return
	 */
	@Override
	public String createTempTicket(String accessToken, int expireSeconds, int sceneId) {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		Map<String, Integer> intMap = new HashMap<>();
		intMap.put("scene_id", sceneId);
		Map<String, Map<String, Integer>> mapMap = new HashMap<>();
		mapMap.put("scene", intMap);
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("expire_seconds", expireSeconds);
		paramsMap.put("action_name", QRCodeConstant.QR_SCENE);
		paramsMap.put("action_info", mapMap);
		String data = JsonUtil.toJsonString(paramsMap);
		data = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD, WechatConfig.CREATE_TICKET_PATH, params, data, null);
		WechatQRCode wechatQRCode = null;
		try {
			wechatQRCode = JsonUtil.fromJsonString(data, WechatQRCode.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return wechatQRCode == null ? null : wechatQRCode.getTicket();
	}

	/**
	 * 创建永久二维码(数字)
	 * 
	 * @param accessToken
	 * @param sceneId
	 *            场景Id
	 * @return
	 */
	@Override
	public String createForeverTicket(String accessToken, int sceneId) {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// output data
		Map<String, Integer> intMap = new HashMap<>();
		intMap.put("scene_id", sceneId);
		Map<String, Map<String, Integer>> mapMap = new HashMap<>();
		mapMap.put("scene", intMap);
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("action_name", QRCodeConstant.QR_LIMIT_SCENE);
		paramsMap.put("action_info", mapMap);
		String data = JsonUtil.toJsonString(paramsMap);
		data = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD, WechatConfig.CREATE_TICKET_PATH, params, data, null);
		WechatQRCode wechatQRCode = null;
		try {
			wechatQRCode = JsonUtil.fromJsonString(data, WechatQRCode.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return wechatQRCode == null ? null : wechatQRCode.getTicket();
	}

	/**
	 * 创建永久二维码(字符串)
	 * 
	 * @param accessToken
	 * @param sceneStr 场景str
	 * @return
	 */
	@Override
	public String createForeverStrTicket(String accessToken, String sceneStr) {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		// output data
		Map<String, String> intMap = new HashMap<>();
		intMap.put("scene_str", sceneStr);
		Map<String, Map<String, String>> mapMap = new HashMap<>();
		mapMap.put("scene", intMap);
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("action_name", QRCodeConstant.QR_LIMIT_STR_SCENE);
		paramsMap.put("action_info", mapMap);
		String data = JsonUtil.toJsonString(paramsMap);
		data = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD, WechatConfig.CREATE_TICKET_PATH, params, data, null);
		WechatQRCode wechatQRCode = null;
		try {
			wechatQRCode = JsonUtil.fromJsonString(data, WechatQRCode.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return wechatQRCode == null ? null : wechatQRCode.getTicket();
	}
	
	/**
	 * 获取二维码ticket后，通过ticket换取二维码图片展示
	 * 
	 * @param ticket
	 * @return 二维码图片地址
	 * @throws Exception
	 */
	@Override
	public String showQrcode(String accessToken, String ticket) throws Exception {
		return toShortQRCodeurl(accessToken, HttpReqUtil.setParmas(params(ticket), WechatConfig.SHOW_QRCODE_PATH, null));
	}

	/**
	 * 下载二维码
	 * 
	 * @param ticket
	 * @param savePath  保存的路径,例如 F:\\phil\phil.jpg
	 * @return Result.success = true 表示下载图片下载成功
	 */
	@Override
	public WechatResult downshowQrcode(String ticket, String savePath) throws Exception {
		return HttpReqUtil.downMeaterMetod(params(ticket), SystemConstant.GET_METHOD, WechatConfig.SHOW_QRCODE_PATH,
				savePath, null);
	}

	private TreeMap<String, String> params(String ticket) {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("ticket", IOUtil.urlEncode(ticket, SystemConstant.DEFAULT_CHARACTER_ENCODING));
		return params;
	}
	
	/**
	 * 长链接转短链接
	 * 
	 * @param accessToken
	 * @param longUrl  长链接
	 * @return
	 */
	private String toShortQRCodeurl(String accessToken, String longUrl) {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("access_token", accessToken);
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("action", QRCodeConstant.LONG2SHORT);
		paramsMap.put("long_url", longUrl);
		String data = JsonUtil.toJsonString(paramsMap);
		String result = HttpReqUtil.HttpsDefaultExecute(SystemConstant.POST_METHOD, WechatConfig.WECHAT_SHORT_QRCODE_URL,
				params, data, null);
		WechatQRCodeShortUrl wechatQRCodeShortUrl = JsonUtil.fromJsonString(result, WechatQRCodeShortUrl.class);
		return wechatQRCodeShortUrl.getShort_url();
	}
}
