package com.phil.common.params;

import java.util.Map;

/**
 * 基本请求抽象类
 * @author phil
 * @date  2017年7月2日
 *
 */
public abstract class AbstractParams {
	
	/**
	 * 返回请求参数
	 * @return
	 */
	public abstract Map<String,String> getParams() throws Exception;
}
