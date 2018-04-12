package com.phil.wechat.msg.model.resp;

import java.util.List;

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

	/**
	 * 
	 * @author phil
	 * @date 2017年7月19日
	 *
	 */
	public class Articles {

		private List<Item> list;

		public List<Item> getList() {
			return list;
		}

		public void setList(List<Item> list) {
			this.list = list;
		}

		/**
		 * 图文 model
		 * 
		 * @author phil
		 *
		 */
		public class Item {
			// 图文消息名称
			private String Title;
			// 图文消息描述
			private String Description;
			// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
			private String PicUrl;
			// 点击图文消息跳转链接
			private String Url;

			public String getTitle() {
				return Title;
			}

			public void setTitle(String title) {
				Title = title;
			}

			public String getDescription() {
				return Description;
			}

			public void setDescription(String description) {
				Description = description;
			}

			public String getPicUrl() {
				return PicUrl;
			}

			public void setPicUrl(String picUrl) {
				PicUrl = picUrl;
			}

			public String getUrl() {
				return Url;
			}

			public void setUrl(String url) {
				Url = url;
			}
		}

	}
}
