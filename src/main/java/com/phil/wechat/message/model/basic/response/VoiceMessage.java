/**
 * FileName: Voice
 * Author:   Phil
 * Date:     8/1/2018 17:34
 * Description: Voice Message Replies
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.basic.response;

import com.phil.modules.converter.MediaIdConverter;
import com.phil.wechat.message.constant.WechatMessageConstant;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉
 * 〈Voice Message Replies〉
 *
 * @author Phil
 * @create 8/1/2018 17:34
 * @since 1.0.0
 */
@Getter
@Setter
@XStreamAlias("xml")
public class VoiceMessage extends ResponseMessage {

    private static final long serialVersionUID = -1956619978457852167L;

    @XStreamAlias("Voice")
    @XStreamConverter(value = MediaIdConverter.class)
    private String mediaId;

    @Override
    public String setMsgType() {
        return WechatMessageConstant.RESP_MESSAGE_TYPE_VOICE;
    }
}
