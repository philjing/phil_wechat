/**
 * 
 */
package com.phil.wechatpay.service.impl;

import org.springframework.stereotype.Service;

import com.phil.common.config.SystemConfig;
import com.phil.common.config.WechatConfig;
import com.phil.common.util.SignatureUtil;
import com.phil.common.util.XmlUtil;
import com.phil.wechatpay.model.rep.AbstractPayParams;
import com.phil.wechatpay.service.WechatPayService;

/**
 * @author phil
 * @date  2017年7月25日
 *
 */
@Service
public class WechatPayServiceImpl implements WechatPayService {
	
	public String abstractPayToXml(AbstractPayParams params){
		String sign = SignatureUtil.createSign(params, WechatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
		params.setSign(sign);
		return XmlUtil.toSplitXml(params);
	}
}
