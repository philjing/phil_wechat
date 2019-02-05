/**
 * FileName: WechatTemplateMessageService
 * Author:   Phil
 * Date:     11/21/2018 1:57 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.service.impl;

import com.google.gson.JsonObject;
import com.phil.modules.config.WechatProperties;
import com.phil.modules.config.WechatPropertiesConstant;
import com.phil.modules.util.HttpUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.message.model.template.response.TemplateIdResult;
import com.phil.wechat.message.model.template.response.TemplateMessageResult;
import com.phil.wechat.message.service.WechatTemplateMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 1:57 PM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatTemplateMessageServiceImpl implements WechatTemplateMessageService {

    @Override
    public TemplateMessageResult sendTemplate(String accessToken, String data) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.SEND_TEMPLATE_MESSAGE_URL),
                params, data);
        return JsonUtil.fromJson(result, TemplateMessageResult.class);
    }

    @Override
    public TemplateIdResult getTemplateId(String accessToken, String templateIdShort) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        JsonObject data = new JsonObject();
        data.addProperty("template_id_short", templateIdShort);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.ADD_TEMPLATE_URL),
                params, data.toString());
        return JsonUtil.fromJson(result, TemplateIdResult.class);
    }
}
