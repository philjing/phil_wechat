/**
 * FileName: MassNews
 * Author:   Phil
 * Date:     11/21/2018 8:14 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈群发时用到的图文消息素材〉
 *
 * @author Phil
 * @create 11/21/2018 8:14 PM
 * @since 1.0
 */
@Getter
@Setter
public class MassNews implements Serializable {

    private static final long serialVersionUID = -5379861922920307510L;

    private List<MassNewsArticle> articles = new ArrayList<>();

    public void addArticle(MassNewsArticle article){
        this.articles.add(article);
    }

    /**
     * <pre>
     * 群发图文消息article
     * 1. thumbMediaId  (必填) 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     * 2. author          图文消息的作者
     * 3. title           (必填) 图文消息的标题
     * 4. contentSourceUrl 在图文消息页面点击“阅读原文”后的页面链接
     * 5. content (必填)  图文消息页面的内容，支持HTML标签
     * 6. digest          图文消息的描述
     * 7, showCoverPic  是否显示封面，true为显示，false为不显示
     * 8, needOpenComment  Uint32 是否打开评论，0不打开，1打开
     * 9, onlyFansCanComment  Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
     * </pre>
     */
    @Getter
    @Setter
    public static class MassNewsArticle {
        /**
         * (必填) 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
         */
        @SerializedName("thumb_media_id")
        private String thumbMediaId;
        /**
         * 图文消息的作者
         */
        private String author;
        /**
         * (必填) 图文消息的标题
         */
        private String title;
        /**
         * 在图文消息页面点击“阅读原文”后的页面链接
         */
        @SerializedName("content_source_url")
        private String contentSourceUrl;
        /**
         * (必填) 图文消息页面的内容，支持HTML标签
         */
        private String content;
        /**
         * 图文消息的描述
         */
        private String digest;
        /**
         * 是否显示封面，true为显示，false为不显示
         */
        @SerializedName("show_cover_pic")
        private int showCoverPic;

        @SerializedName("need_open_comment")
        private int needOpenComment;

        @SerializedName("only_fans_can_comment")
        private int onlyFansCanComment;
    }

}
