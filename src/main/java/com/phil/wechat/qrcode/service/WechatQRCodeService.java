package com.phil.wechat.qrcode.service;

import com.phil.wechat.base.result.WechatResult;

public interface WechatQRCodeService {

	/**
	 * 创建临时带参数二维码
	 * 
	 * @param accessToken
	 * @expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * @param sceneId 场景Id
	 * @return
	 */
	public String createTempTicket(String accessToken, int expireSeconds, int sceneId);

	/**
	 * 创建永久二维码(数字)
	 * 
	 * @param accessToken
	 * @param sceneId
	 *            场景Id
	 * @return
	 */
	public String createForeverTicket(String accessToken, int sceneId);

	/**
	 * 创建永久二维码(字符串)
	 * 
	 * @param accessToken
	 * @param sceneStr
	 *            场景str
	 * @return
	 */
	public String createForeverStrTicket(String accessToken, String sceneStr);
	
	/**
	 * 获取二维码ticket后，通过ticket换取二维码图片展示
	 * 
	 * @param ticket
	 * @return 二维码图片地址
	 * @throws Exception
	 */
	public String showQrcode(String accessToken, String ticket) throws Exception;

	/**
	 * 下载二维码
	 * 
	 * @param ticket
	 * @param savePath
	 *            保存的路径,例如 F:\\phil\phil.jpg
	 * @return Result.success = true 表示下载图片下载成功
	 */
	public WechatResult downshowQrcode(String ticket, String savePath) throws Exception;
}
