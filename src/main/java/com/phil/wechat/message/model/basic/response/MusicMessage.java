/**
 * FileName: MusicMsg
 * Author:   Phil
 * Date:     8/1/2018 17:41
 * Description: Music Message Replies
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
 * 〈Music Message Replies〉
 *
 * @author Phil
 * @create 8/1/2018 17:41
 * @since 1.0.0
 */
@Getter
@Setter
@XStreamAlias("xml")
public class MusicMessage extends ResponseMessage {

    private static final long serialVersionUID = 1553067077919661894L;

    @XStreamAlias("Music")
    private Music music;

    @Override
    public String setMsgType() {
        return WechatMessageConstant.RESP_MESSAGE_TYPE_MUSIC;
    }

    @Getter
    @Setter
//    @XStreamAlias("Music")
    public static class Music {

        @XStreamAlias("Title")
        @XStreamConverter(value = CDATAConvert.class)
        private String title;

        @XStreamAlias("Description")
        @XStreamConverter(value = CDATAConvert.class)
        private String description;

        @XStreamAlias("ThumbMediaId")
        @XStreamConverter(value = CDATAConvert.class)
        private String thumbMediaId;

        @XStreamAlias("MusicUrl")
        @XStreamConverter(value = CDATAConvert.class)
        private String musicUrl;

        @XStreamAlias("HQMusicUrl")
        @XStreamConverter(value = CDATAConvert.class)
        private String hqMusicUrl;

        public Music(String thumbMediaId) {
            this.thumbMediaId = thumbMediaId;
        }

    }


}
