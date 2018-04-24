package com.phil.wechat.auth.model.req;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import com.phil.modules.constant.SystemConstant;
import com.phil.modules.util.IOUtil;
import com.phil.wechat.base.param.AbstractParams;

/**
 * 获取授权code请求参数
 * 
 * @author phil
 * @date 2017年7月2日
 *
 */
public class AuthCodeParams extends AbstractParams {
	
	private static final long serialVersionUID = 6313379843885615765L;
	public static final String SCOPE_SNSAPIBASE = "snsapi_base"; // snsapi_base(不需要弹出授权页面,只能获取openid)
	public static final String SCOPE_SNSPAIUSERINFO = "snsapi_userinfo"; // 弹出授权页面(获取用户基本信息)
	private String appid;
	private String redirect_uri; // 使用urlencode对链接进行处理
	private String response_type = "code";
	private String scope;
	private String state;

	public AuthCodeParams() {
		super();
	}

	public AuthCodeParams(String appid, String redirect_uri, String response_type, String scope, String state) {
		super();
		this.appid = appid;
		this.redirect_uri = redirect_uri;
		this.response_type = response_type;
		this.scope = scope;
		this.state = state;
	}

	/**
	 * 参数组装
	 * 
	 * @return
	 */
	public Map<String, String> getParams() throws UnsupportedEncodingException {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("appid", this.appid);
		params.put("redirect_uri", IOUtil.urlEncode(this.redirect_uri, SystemConstant.DEFAULT_CHARACTER_ENCODING));
		params.put("response_type", this.response_type);
		params.put("scope", this.scope);
		params.put("state", this.state);
		return params;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getResponse_type() {
		return response_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}