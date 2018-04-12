package com.phil.wechat.base.param;

import java.io.Serializable;
import java.util.Map;

/**
 * 基本请求抽象类
 * @author phil
 * @date  2017年7月2日
 *
 */
public abstract class AbstractParams implements Serializable{

	private static final long serialVersionUID = 3375127810872852675L;

	/**
	 * 返回请求参数
	 * @return
	 */
	public abstract Map<String,String> getParams() throws Exception;
}
