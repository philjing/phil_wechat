package com.phil.wechat.message.model.template.request;

import com.google.gson.annotations.SerializedName;
import com.phil.modules.annotation.NotRequire;
import com.phil.modules.util.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息
 *
 * @author phil
 * @date 2017年7月2日
 */
@Getter
@Setter
@NoArgsConstructor
public class WechatTemplateMessage implements Serializable {

    private static final long serialVersionUID = -1035536196053732735L;

    @SerializedName("touser")
    private String touser; //接收者openid

    @SerializedName("template_id")
    private String templateId; //模板ID

    @NotRequire//只是个标识
    private String url; //模板跳转链接

    // "miniprogram":{ 未加入
    // "appid":"xiaochengxuappid12345",
    // "pagepath":"index?foo=bar"
    // },

    private Map<String, Map<String, String>> data; //data数据

    public WechatTemplateMessage(String templateId, String url) {
        this.templateId = templateId;
        this.url = url;
    }

    /**
     * 参数
     *
     * @param value
     * @param color 可不填
     * @return
     */
    public Map<String, String> item(String value, String color) {
        Map<String, String> params = new HashMap<>();
        params.put("value", value);
        params.put("color", color == null ? "#000000" : color);
        return params;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }
}
