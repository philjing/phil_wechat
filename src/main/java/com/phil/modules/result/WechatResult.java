/**
 * FileName: WechatResult
 * Author:   Phil
 * Date:     11/27/2018 10:44 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.result;

import com.phil.modules.util.XmlUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/27/2018 10:44 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class WechatResult implements Serializable {

    private static final long serialVersionUID = -1470168749001663805L;

    //回复消息的Xml
    private String respXml;

    //需要返回的bean
    private Object response;

    //成功与否
    private boolean success;

    //是否是xml
    private boolean xml;

    //是否需要发模板消息
    private boolean template;

    //是否需要发图文消息
    private boolean news;

    //比如推送模板消息的json等
    private String respJson;

    //需要返回的bean的xml
    public String toXml() {
        if (response == null) {
            return "";
        }
        return XmlUtil.toXml(response);
    }
}
