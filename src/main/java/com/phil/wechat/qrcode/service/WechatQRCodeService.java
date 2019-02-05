/**
 * FileName: WechatQRCodeService
 * Author:   Phil
 * Date:     11/21/2018 11:59 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.qrcode.service;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 11:59 AM
 * @since 1.0
 */
public interface WechatQRCodeService {

    /**
     * 创建临时带参数二维码
     *
     * @param accessToken
     * @param sceneId     场景Id
     * @return
     * @expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    String createTempTicket(String accessToken, int sceneId, int expireSeconds);

    /**
     * 创建临时带参数二维码(字符串)
     *
     * @param accessToken
     * @param sceneStr    场景Id
     * @return
     * @expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    String createTempTicket(String accessToken, String sceneStr, int expireSeconds);

    /**
     * 创建永久二维码(数字)
     *
     * @param accessToken
     * @param sceneId     场景Id
     * @return
     */
    String createForeverTicket(String accessToken, int sceneId);

    /**
     * 创建永久二维码(字符串)
     *
     * @param accessToken
     * @param sceneStr    场景str
     * @return
     */
    String createForeverTicket(String accessToken, String sceneStr);

    /**
     * 获取二维码ticket后，通过ticket换取二维码图片展示
     *
     * @param accessToken
     * @param ticket
     * @param isShortUrl  是否需要展示
     * @return
     */
    String showQrCode(String accessToken, String ticket, boolean isShortUrl);

}
