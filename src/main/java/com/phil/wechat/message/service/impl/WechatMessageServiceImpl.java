/**
 * FileName: WechatMessageServiceImpl
 * Author:   Phil
 * Date:     11/21/2018 1:36 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.service.impl;


import com.phil.modules.util.DateUtils;
import com.phil.modules.result.WechatResult;
import com.phil.wechat.message.model.basic.request.RequestMessage;
import com.phil.wechat.message.model.basic.response.*;
import com.phil.wechat.message.service.WechatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 1:36 PM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatMessageServiceImpl implements WechatMessageService {

    /**
     * 默认执行的消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    @Override
    public WechatResult defaultMsg(RequestMessage message) {
        return null;
    }

    /**
     * 回复文本消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    @Override
    public String sendTextMsg(RequestMessage message) {
        TextMessage text = new TextMessage();
        text.setCreateTime(DateUtils.getCurrentTimeMillis());
        text.setToUserName(message.getFromUserName());
        text.setFromUserName(message.getToUserName());
        text.setContent("未匹配到关键词，你发送的文本是" + message.getContent().trim());
        return text.toXml();
    }

    /**
     * 回复图片消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    @Override
    public String sendImageMsg(RequestMessage message) {
        ImageMessage image = new ImageMessage();
        image.setCreateTime(DateUtils.getCurrentTimeMillis());
        image.setToUserName(message.getFromUserName());
        image.setFromUserName(message.getToUserName());
        image.setMediaId(message.getMediaId());
        return image.toXml();
    }

    /**
     * 回复语音消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    @Override
    public String sendVoiceMsg(RequestMessage message) {
        VoiceMessage voice = new VoiceMessage();
        voice.setCreateTime(DateUtils.getCurrentTimeMillis());
        voice.setToUserName(message.getFromUserName());
        voice.setFromUserName(message.getToUserName());
        voice.setMediaId(message.getMediaId());
        return voice.toXml();
    }

    /**
     * 回复视频消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    @Override
    public String sendVideoMsg(RequestMessage message) {
        VideoMessage video = new VideoMessage();
        video.setCreateTime(DateUtils.getCurrentTimeMillis());
        video.setToUserName(message.getFromUserName());
        video.setFromUserName(message.getToUserName());
        video.setVideo(new VideoMessage.Video(message.getMediaId()));
        return video.toXml();
    }

    /**
     * 回复音乐消息
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    @Override
    public String sendMusicMsg(RequestMessage message) {
        MusicMessage music = new MusicMessage();
        music.setCreateTime(DateUtils.getCurrentTimeMillis());
        music.setToUserName(message.getFromUserName());
        music.setFromUserName(message.getToUserName());
        music.setMusic(new MusicMessage.Music(message.getMediaId()));
        return music.toXml();
    }

    /**
     * 回复图文消息 (改进)
     *
     * @param message 基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    @Override
    public String sendNewsMsg(RequestMessage message) {
        NewsMessage news = new NewsMessage();
        news.addArticle(new NewsMessage.Item());
        news.addArticle(new NewsMessage.Item());
        return news.toXml();
    }

    /**
     * 用户关注时调用的方法
     *
     * @param message 基础消息
     * @return
     */
    @Override
    public WechatResult doSubscribe(RequestMessage message) {
        WechatResult result = new WechatResult();
        TextMessage text = new TextMessage();
        text.setCreateTime(DateUtils.getCurrentTimeMillis());
        text.setToUserName(message.getFromUserName());
        text.setFromUserName(message.getToUserName());
        text.setContent("未关注时进行关注再事件二维码参数" + message.getEventKey());//
        if (!Objects.isNull(message.getEventKey())) { //如果有二维码参数的话
            result.setTemplate(true);
            text.setContent("未关注时进行关注再事件二维码参数" + message.getEventKey().replace("qrscene_", ""));
        }
        result.setResponse(text);
        result.setXml(true);
        return result;
    }

    @Override
    public String doUnsubscribe(RequestMessage message) {
        return null;
    }

    @Override
    public WechatResult doScan(RequestMessage message) {
        WechatResult result = new WechatResult();
        TextMessage text = new TextMessage();
        text.setCreateTime(DateUtils.getCurrentTimeMillis());
        text.setToUserName(message.getFromUserName());
        text.setFromUserName(message.getToUserName());
        text.setContent("已关注事件二维码参数" + message.getEventKey());// 自动回复
        result.setResponse(text);
        result.setXml(true);
        if (!Objects.isNull(message.getEventKey())) { //如果有二维码参数的话
            result.setTemplate(true);
        }
        return result;
    }

    /**
     * 上报地理位置事件
     *
     * @param message
     * @return
     */
    @Override
    public String doLocation(RequestMessage message) {
        return null;
    }

    /**
     * 点击菜单拉取消息时的事件推送
     *
     * @param message
     * @return
     */
    @Override
    public String doClick(RequestMessage message) {
        return null;
    }

    /**
     * 点击菜单跳转链接时的事件推送
     *
     * @param message
     * @return
     */
    @Override
    public String doView(RequestMessage message) {
        return null;
    }

//    private String toTemplate(RequestMessage message, WechatTemplateMessage template) {
//        Map<String, Map<String, String>> data = new HashMap<>();
//        data.put("keyword1", template.item(message.getEventKey(), null));
//        data.put("keyword2", template.item(DateUtils.getDateString(DateUtils.DATE_FORMAT), null));
//        data.put("keyword3", template.item(new Random().nextInt(10) + "", null));
//        template.setTouser(message.getFromUserName());
////        template.setTemplateId("bFpV7w3DhSUhlZtu2FZAlijoQs97Z6DA_PpCqOqmPfQ");
////        template.setUrl("");
//        template.setData(data);
//        return template.toJson();
//    }
}
