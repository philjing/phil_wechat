/**
 * 
 */
package com.phil.wechatpay.model.rep;

import java.io.Serializable;

/**
 * 二维码链接转成短链接请求参数
 * @author phil
 * @date  2017年8月1日
 *
 */
public class PayShortUrlParams extends AbstractPayParams implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8485062553285184505L;
	
	private String long_url;

	public String getLong_url() {
		return long_url;
	}

	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}
	
	

}
