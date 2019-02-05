/**
 * FileName: NewsMsg
 * Author:   Phil
 * Date:     8/2/2018 11:02
 * Description:
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

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Phil
 * @create 8/2/2018 11:02
 * @since 1.0.0
 */
@Getter
@Setter
@XStreamAlias("xml")
public class NewsMessage extends ResponseMessage {

    private static final long serialVersionUID = -2351122602671657550L;

    @XStreamAlias("Articles")
    protected final List<Item> articles = new ArrayList<>();

    @XStreamAlias("ArticleCount")
    private int articleCount;

    @Override
    public String setMsgType() {

        return WechatMessageConstant.RESP_MESSAGE_TYPE_NEWS;
    }

    public void addArticle(Item item) {
        this.articles.add(item);
        this.articleCount = this.articles.size();
    }

    @Getter
    @Setter
    @XStreamAlias("item")
    public static class Item {

        @XStreamAlias("Title")
        @XStreamConverter(value = CDATAConvert.class)
        private String title;

        @XStreamAlias("Description")
        @XStreamConverter(value = CDATAConvert.class)
        private String description;

        @XStreamAlias("PicUrl")
        @XStreamConverter(value = CDATAConvert.class)
        private String picurl;

        @XStreamAlias("Url")
        @XStreamConverter(value = CDATAConvert.class)
        private String url;


    }
}
