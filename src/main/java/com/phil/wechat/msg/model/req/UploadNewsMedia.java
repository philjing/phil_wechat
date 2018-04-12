package com.phil.wechat.msg.model.req;

/** 
 * 上传图文消息素材实体类 
 * @author phil 
 * @date  2017年9月20日 
 */  
public class UploadNewsMedia {  
      
    private String title; // 图文消息的标题  
    private String thumb_media_id; // 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得  
    private String author; // 图文消息的作者  
    private String digest; // 图文消息的描述  
    private int show_conver_pic; // 是否显示为封面 1表示显示为封面 0 不显示为封面  
    private String content_source_url; // 图文消息点击阅读原文的链接  
    private String content; // 图文消息页面的内容，支持HTML标签  
  
    public String getThumb_media_id() {  
        return thumb_media_id;  
    }  
  
    public void setThumb_media_id(String thumbMediaId) {  
        thumb_media_id = thumbMediaId;  
    }  
  
    public String getAuthor() {  
        return author;  
    }  
  
    public void setAuthor(String author) {  
        this.author = author;  
    }  
  
    public String getTitle() {  
        return title;  
    }  
  
    public void setTitle(String title) {  
        this.title = title;  
    }  
  
    public String getContent_source_url() {  
        return content_source_url;  
    }  
  
    public void setContent_source_url(String contentSourceUrl) {  
        content_source_url = contentSourceUrl;  
    }  
  
    public String getContent() {  
        return content;  
    }  
  
    public void setContent(String content) {  
        this.content = content;  
    }  
  
    public String getDigest() {  
        return digest;  
    }  
  
    public void setDigest(String digest) {  
        this.digest = digest;  
    }  
  
    public int getShow_conver_pic() {  
        return show_conver_pic;  
    }  
  
    public void setShow_conver_pic(int showConverPic) {  
        show_conver_pic = showConverPic;  
    }  
}  
