/**
 * FileName: RequestMessage
 * Author:   Phil
 * Date:     11/27/2018 11:55 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.basic.request;

/**
 * 〈一句话功能简述〉<br>
 * * <pre>
 *  * 微信推送过来的所有消息，也是同步回复给用户的消息，xml格式
 *  *</pre>
 * @author Phil
 * @create 11/27/2018 11:55 PM
 * @since 1.0
 */

import com.phil.modules.converter.CDATAConvert;
import com.phil.modules.util.XmlUtil;
import com.phil.wechat.message.constant.WechatMessageConstant;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class RequestMessage implements Serializable {

    private static final long serialVersionUID = -9089207477606773120L;

    /**
     * 微信推送过来的消息的xml的element所对应的属性
     */
    @XStreamAlias("ToUserName")
    @XStreamConverter(value = CDATAConvert.class)
    private String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(value = CDATAConvert.class)
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Long createTime;

    /**
     * <pre>
     * 当接受用户消息时，可能会获得以下值：
     * {@link WechatMessageConstant#REQ_MESSAGE_TYPE_TEXT}
     * {@link WechatMessageConstant#REQ_MESSAGE_TYPE_IMAGE}
     * {@link WechatMessageConstant#REQ_MESSAGE_TYPE_VOICE}
     * {@link WechatMessageConstant#REQ_MESSAGE_TYPE_VIDEO}
     * {@link WechatMessageConstant#REQ_MESSAGE_TYPE_LOCATION}
     * {@link WechatMessageConstant#REQ_MESSAGE_TYPE_LINK}
     * {@link WechatMessageConstant#REQ_MESSAGE_TYPE_EVENT}
     * </pre>
     *
     * <pre>
     * 当发送消息的时候使用：
     * {@link WechatMessageConstant#RESP_MESSAGE_TYPE_TEXT}
     * {@link WechatMessageConstant#RESP_MESSAGE_TYPE_IMAGE}
     * {@link WechatMessageConstant#RESP_MESSAGE_TYPE_VOICE}
     * {@link WechatMessageConstant#RESP_MESSAGE_TYPE_VIDEO}
     * {@link WechatMessageConstant#RESP_MESSAGE_TYPE_NEWS}
     * {@link WechatMessageConstant#RESP_MESSAGE_TYPE_MUSIC}
     * </pre>
     *
     * @return
     */
    @XStreamAlias("MsgType")
    @XStreamConverter(value = CDATAConvert.class)
    private String msgType;

    @XStreamAlias("Content")
    @XStreamConverter(value = CDATAConvert.class)
    private String content;

    @XStreamAlias("MsgId")
    private Long msgId;

    @XStreamAlias("PicUrl")
    @XStreamConverter(value = CDATAConvert.class)
    private String picUrl;

    @XStreamAlias("MediaId")
    @XStreamConverter(value = CDATAConvert.class)
    private String mediaId;

    @XStreamAlias("Format")
    @XStreamConverter(value = CDATAConvert.class)
    private String format;

    @XStreamAlias("ThumbMediaId")
    @XStreamConverter(value = CDATAConvert.class)
    private String thumbMediaId;

    @XStreamAlias("Location_X")
    private Double locationX;

    @XStreamAlias("Location_Y")
    private Double locationY;

    @XStreamAlias("Scale")
    private Double scale;

    @XStreamAlias("Label")
    @XStreamConverter(value = CDATAConvert.class)
    private String label;

    @XStreamAlias("Title")
    @XStreamConverter(value = CDATAConvert.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = CDATAConvert.class)
    private String description;

    @XStreamAlias("Url")
    @XStreamConverter(value = CDATAConvert.class)
    private String url;

    @XStreamAlias("Event")
    @XStreamConverter(value = CDATAConvert.class)
    private String event;

    @XStreamAlias("EventKey")
    @XStreamConverter(value = CDATAConvert.class)
    private String eventKey;

    @XStreamAlias("Ticket")
    @XStreamConverter(value = CDATAConvert.class)
    private String ticket;

    @XStreamAlias("Latitude")
    private Double latitude;

    @XStreamAlias("Longitude")
    private Double longitude;

    @XStreamAlias("Precision")
    private Double precision;

    @XStreamAlias("Recognition")
    @XStreamConverter(value = CDATAConvert.class)
    private String recognition;

    //群发消息返回的结果
    /**
     * 群发的结果
     */
    @XStreamAlias("Status")
    @XStreamConverter(value = CDATAConvert.class)
    private String status;
    /**
     * group_id下粉丝数；或者openid_list中的粉丝数
     */
    @XStreamAlias("TotalCount")
    private Integer totalCount;
    /**
     * 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，filterCount = sentCount + errorCount
     */
    @XStreamAlias("FilterCount")
    private Integer filterCount;
    /**
     * 发送成功的粉丝数
     */
    @XStreamAlias("SentCount")
    private Integer sentCount;
    /**
     * 发送失败的粉丝数
     */
    @XStreamAlias("ErrorCount")
    private Integer errorCount;

    /**
     * 自定义菜单 scancode_push：扫码推事件的事件推送
     */
    @XStreamAlias("ScanCodeInfo")
    private ScanCodeInfo scanCodeInfo;

    /**
     * 自定义菜单 pic_sysphoto：弹出系统拍照发图的事件推送
     */
    @XStreamAlias("SendPicsInfo")
    private SendPicsInfo sendPicsInfo;

    /**
     * 自定义菜单  location_select：弹出地理位置选择器的事件推送
     */
    @XStreamAlias("SendLocationInfo")
    private SendLocationInfo sendLocationInfo;

    public String toXml() {
        return XmlUtil.toXml(this);
    }

    /**
     * 自定义菜单 scancode_push：扫码推事件的事件推送
     */
    @Getter
    @Setter
    @XStreamAlias("ScanCodeInfo")
    public static class ScanCodeInfo {

        /**
         * 扫描类型,qrcode
         */
        @XStreamAlias("ScanType")
        @XStreamConverter(value = CDATAConvert.class)
        private String scanType;

        /**
         * 扫描结果，即二维码对应的字符串信息
         */
        @XStreamAlias("ScanResult")
        @XStreamConverter(value = CDATAConvert.class)
        private String scanResult;
    }

    /**
     * 自定义菜单 pic_sysphoto：弹出系统拍照发图的事件推送
     */
    @Getter
    @Setter
    @XStreamAlias("SendPicsInfo")
    public static class SendPicsInfo {

        @XStreamAlias("Count")
        private Long count;

        @XStreamAlias("PicList")
        protected final List<Item> picList = new ArrayList<>();

        @XStreamAlias("item")
        public static class Item {

            @XStreamAlias("PicMd5Sum")
            @XStreamConverter(value = CDATAConvert.class)
            private String picMd5Sum;
        }
    }

    /**
     * 自定义菜单  location_select：弹出地理位置选择器的事件推送
     */
    @Getter
    @Setter
    @XStreamAlias("SendLocationInfo")
    public static class SendLocationInfo {

        @XStreamAlias("Location_X")
        @XStreamConverter(value = CDATAConvert.class)
        private String locationX;

        @XStreamAlias("Location_Y")
        @XStreamConverter(value = CDATAConvert.class)
        private String locationY;

        @XStreamAlias("Scale")
        @XStreamConverter(value = CDATAConvert.class)
        private String scale;

        @XStreamAlias("Label")
        @XStreamConverter(value = CDATAConvert.class)
        private String label;

        @XStreamAlias("Poiname")
        @XStreamConverter(value = CDATAConvert.class)
        private String poiname;

    }
}
