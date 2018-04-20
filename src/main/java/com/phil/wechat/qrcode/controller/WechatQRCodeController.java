package com.phil.wechat.qrcode.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phil.wechat.base.controller.BaseController;
import com.phil.wechat.qrcode.service.WechatQRCodeService;

@Controller
@RequestMapping("api/wechat/qrcode")
public class WechatQRCodeController extends BaseController {
	
	@Autowired
	private WechatQRCodeService wechatQRCodeService;
	
	@PostMapping("createTempTicket")
	public Map<String, Object> createTempTicket(Map<String, Object> params){
		Map<String, Object> data = new HashMap<>();
		String accessToken = "";
		if(org.springframework.util.StringUtils.isEmpty(params.get("sceneId"))) {
			data.put("code", -1);
			data.put("msg", "参数为空");
			return data;
		} else {	
			try {
				String ticket = wechatQRCodeService.createTempTicket(accessToken, 2592000, (int)params.get("sceneId"));
				String url = wechatQRCodeService.showQrcode(accessToken, ticket);
				data.put("code", 0);
				data.put("msg", "生成临时二维码成功");
				data.put("data", url);
			} catch (Exception e) {
				data.put("code", -1);
				data.put("msg", "生成临时二维码失败");
			}
			return data;
		}
	}
}
