package com.phil.wechat.msg.model.resp;

import com.phil.wechat.msg.model.resp.bean.Articles;

/**
 * 图文消息
 * 
 * @author phil
 *
 */
public class RespNewsMsg extends RespAbstractMsg {

	// 图片消息个数，限制在8条以内
	private int ArticleCount;

	private Articles articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	@Override
	public String setMsgType() {
		return "news";
	}
}
