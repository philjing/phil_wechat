package com.phil.modules.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.phil.modules.constant.SystemConstant;

/**
 * IO流工具类
 * 
 * @author phil
 * @date 2018年4月22日
 *
 */
public class IOUtil {
	
	/**
	 * 将输入流转换为字符串
	 * 
	 * @param is
	 *            待转换为字符串的输入流
	 * @return 由输入流转换String的字符串
	 * @throws IOException
	 */
	public static String inputStreamToString(InputStream inputStream, String encoding) throws IOException {
		if(StringUtils.isEmpty(encoding)) {
			encoding = SystemConstant.DEFAULT_CHARACTER_ENCODING;
		}
		return IOUtils.toString(inputStream, encoding);
	}

	/**
	 * 将字符串转换为输入流
	 * 
	 * @param sInputString
	 *            待转换为输入流的字符串
	 * @return
	 * @throws IOException
	 */
	public static InputStream toInputStream(String inputStr, String encoding) throws IOException {
		if (StringUtils.isEmpty(inputStr)) {
			return null;
		}
		if(StringUtils.isEmpty(encoding)) {
			encoding = SystemConstant.DEFAULT_CHARACTER_ENCODING;
		}
		return IOUtils.toInputStream(inputStr, encoding);
	}
	

	/**
	 * 编码
	 * 
	 * @param source
	 * @param encode
	 * @return
	 */
	public static String urlEncode(String source, String encode) {
		String result = source;
		try {
			result = URLEncoder.encode(source, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将输入流转换字节数组
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
		return IOUtils.toByteArray(inputStream);
	}

}
