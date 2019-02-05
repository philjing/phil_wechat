/**
 * FileName: AbstractMsg
 * Author:   Phil
 * Date:     8/2/2018 10:46
 * Description: 消息的基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.basic.response;

import com.phil.modules.converter.CDATAConvert;
import com.phil.modules.util.XmlUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉
 * 〈消息的基类〉
 *
 * @author Phil
 * @create 8/2/2018 10:46
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public abstract class ResponseMessage implements Serializable {

    private static final long serialVersionUID = 4684307234530530252L;

    @XStreamAlias("ToUserName")
    @XStreamConverter(value = CDATAConvert.class)
    private String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(value = CDATAConvert.class)
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private long createTime;

    @XStreamAlias("MsgType")
    @XStreamConverter(value = CDATAConvert.class)
    private String msgType = setMsgType();

    public abstract String setMsgType();

    public String toXml() {
        return XmlUtil.toXml(this);
    }
}
