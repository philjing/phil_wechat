package com.phil.wechat.message.model.template.request;

import java.util.TreeMap;

/**
 * 模板基础选项
 * * {
 * * "touser":"OPENID",
 * * "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
 * * "url":"http://weixin.qq.com/download",
 * * "miniprogram":{
 * * "appid":"xiaochengxuappid12345",
 * * "pagepath":"index?foo=bar"
 * * },
 * * "data":{
 * * "first": {
 * * "value":"恭喜你购买成功！",
 * * "color":"#173177"
 * * },
 * * "keyword1":{
 * * "value":"巧克力",
 * * "color":"#173177"
 * * },
 * * "keyword2": {
 * * "value":"39.8元",
 * * "color":"#173177"
 * * },
 * * "keyword3": {
 * * "value":"2014年9月22日",
 * * "color":"#173177"
 * * },
 * * "remark":{
 * * "value":"欢迎再次购买！",
 * * "color":"#173177"
 * * }
 * * }
 * * }
 *
 * @author phil
 * @date 2017年8月1日
 */
public class WechatTemplateData {

    /**
     * 获取参数
     *
     * @param color 文字的颜色(可不填)
     * @param value 文字的值
     * @return
     */
    public static TreeMap<String, String> thempleItem(String value, String color) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("value", value);
        params.put("color", color);
        return params;
    }
}
