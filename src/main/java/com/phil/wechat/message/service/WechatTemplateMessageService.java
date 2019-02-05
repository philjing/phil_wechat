/**
 * FileName: WechatTemplateService
 * Author:   Phil
 * Date:     11/21/2018 1:56 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.service;

import com.phil.wechat.message.model.template.response.TemplateIdResult;
import com.phil.wechat.message.model.template.response.TemplateMessageResult;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 1:56 PM
 * @since 1.0
 */
public interface WechatTemplateMessageService {

    /**
     * 发送模板消息
     *
     * @param accessToken
     * @param data
     * @return
     */
    TemplateMessageResult sendTemplate(String accessToken, String data);

    /**
     * 获得模板ID
     * {
     * "template_id_short":"TM00015"
     * }
     * {
     * "errcode":0,
     * "errmsg":"ok",
     * "template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"
     * }
     *
     * @param accessToken
     * @param templateIdShort post
     * @return
     */
    TemplateIdResult getTemplateId(String accessToken, String templateIdShort);

}
