/**
 * FileName: WechatMessageService
 * Author:   Phil
 * Date:     11/21/2018 1:33 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.service;

import com.phil.modules.result.WechatResult;
import com.phil.wechat.message.model.basic.request.RequestMessage;

/**
 * 〈微信消息处理，基本消息收发回复、模板消息〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 1:33 PM
 * @since 1.0
 */
public interface WechatMessageService {

    /**
     * 默认执行的消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult defaultMsg(RequestMessage message);

    /**
     * 回复文本消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    String sendTextMsg(RequestMessage message);

    /**
     * 回复图片消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    String sendImageMsg(RequestMessage message);

    /**
     * 回复语音消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    String sendVoiceMsg(RequestMessage message);

    /**
     * 回复视频消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    String sendVideoMsg(RequestMessage message);

    /**
     * 回复音乐消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    String sendMusicMsg(RequestMessage message);

    /**
     * 回复图文消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    String sendNewsMsg(RequestMessage message);

    /**
     * 用户关注时调用的方法
     *
     * @param message 基础消息
     * @return
     */
    WechatResult doSubscribe(RequestMessage message);

    /**
     * 取消关注时调用的方法
     *
     * @param message
     * @return
     */
    String doUnsubscribe(RequestMessage message);

    /**
     * 用户已关注时的事件推送
     *
     * @param message
     * @return
     */
    WechatResult doScan(RequestMessage message);

    /**
     * 上报地理位置事件
     *
     * @param message
     * @return
     */
    String doLocation(RequestMessage message);

    /**
     * 点击菜单拉取消息时的事件推送
     *
     * @param message
     * @return
     */
    String doClick(RequestMessage message);

    /**
     * 点击菜单跳转链接时的事件推送
     *
     * @param message
     * @return
     */
    String doView(RequestMessage message);

}
