package com.phil.wechat.base.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.phil.modules.util.ObjectUtil;

public class BaseController {

	protected static final String _CSRF_RANDOM_TOKEN = "_CSRF_RANDOM_TOKEN";

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(int.class, new PropertiesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(ObjectUtil.toInt(text));
			}

			@Override
			public String getAsText() {
				return getValue().toString();
			}
		});

		binder.registerCustomEditor(long.class, new PropertiesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(ObjectUtil.toLong(text));
			}

			@Override
			public String getAsText() {
				return getValue().toString();
			}
		});

		binder.registerCustomEditor(float.class, new PropertiesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(ObjectUtil.toFloat(text));
			}

			@Override
			public String getAsText() {
				return getValue().toString();
			}
		});

		binder.registerCustomEditor(double.class, new PropertiesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(ObjectUtil.toDouble(text));
			}

			@Override
			public String getAsText() {
				return getValue().toString();
			}
		});

		binder.registerCustomEditor(boolean.class, new PropertiesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(ObjectUtil.toBoolean(text));
			}

			@Override
			public String getAsText() {
				return getValue().toString();
			}
		});

		// binder.registerCustomEditor(Date.class,
		// new CustomDateEditor(this.getCustomDateFormat(), true));
		binder.registerCustomEditor(Date.class, new PropertiesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (StringUtils.isEmpty(text)) {
					setValue(null);
				} else {
					try {
						setValue(DateUtils.parseDateStrictly(text, new String[] { "yyyy-MM-dd", "yyyy/MM/dd" }));
					} catch (Exception e) {
						setValue(null);
					}
				}
			}

			@Override
			public String getAsText() {
				return getValue().toString();
			}
		});
	}

	protected String buildDownloadFileName(String baseName) {
		if (StringUtils.isEmpty(baseName)) {
			return baseName;
		}
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		if (userAgent != null) {
			try {
				if (userAgent.indexOf("firefox") >= 0) {
					return new String(baseName.getBytes("UTF-8"), "iso-8859-1");
				} else {
					return java.net.URLEncoder.encode(baseName, "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
			}
		}
		return baseName;
	}

	protected boolean csrf() {
		String reqRandom = request.getParameter("_random");
		String sessionRandom = (String) request.getSession().getAttribute(_CSRF_RANDOM_TOKEN);
		request.getSession().removeAttribute(_CSRF_RANDOM_TOKEN);
		if (!StringUtils.equals(reqRandom, sessionRandom) || reqRandom == null) {
			this.response.setStatus(403);
			return true;
		}
		return false;
	}

	protected String accessToken() {
		String token = UUID.randomUUID().toString();
		request.getSession().setAttribute(_CSRF_RANDOM_TOKEN, token);
		request.setAttribute("_random", token);
		return token;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
