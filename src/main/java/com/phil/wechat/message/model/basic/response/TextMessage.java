/**
 * FileName: TextMsg
 * Author:   Phil
 * Date:     8/1/2018 17:23
 * Description: Text Messages Replies
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.basic.response;

import com.phil.modules.converter.CDATAConvert;
import com.phil.wechat.message.constant.WechatMessageConstant;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉
 * 〈Text Messages Replies〉
 *
 * @author Phil
 * @create 8/1/2018 17:23
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class TextMessage extends ResponseMessage {

    private static final long serialVersionUID = -4159811830464938745L;

    @XStreamAlias("Content")
    @XStreamConverter(value= CDATAConvert.class)
    private String content;

    @Override
    public String setMsgType() {
        return WechatMessageConstant.RESP_MESSAGE_TYPE_TEXT;
    }
}
