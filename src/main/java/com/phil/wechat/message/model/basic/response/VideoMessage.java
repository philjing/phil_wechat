/**
 * FileName: VideoMsg
 * Author:   Phil
 * Date:     8/1/2018 17:37
 * Description: Video Message Replies
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

/**
 * 〈一句话功能简述〉
 * 〈Video Message Replies〉
 *
 * @author Phil
 * @create 8/1/2018 17:37
 * @since 1.0.0
 */
@Getter
@Setter
@XStreamAlias("xml")
public class VideoMessage extends ResponseMessage {

    private static final long serialVersionUID = 3521644245603474470L;

    @XStreamAlias("Video")
    private Video video;

    @Override
    public String setMsgType() {
        return WechatMessageConstant.RESP_MESSAGE_TYPE_VIDEO;
    }

    @Getter
    @Setter
    public static class Video {

        @XStreamAlias("MediaId")
        @XStreamConverter(CDATAConvert.class)
        private String mediaId;

        @XStreamAlias("Title")
        @XStreamConverter(CDATAConvert.class)
        private String title;

        @XStreamAlias("Description")
        @XStreamConverter(CDATAConvert.class)
        private String description;

        public Video(String mediaId) {
            this.mediaId = mediaId;
        }
    }
}
