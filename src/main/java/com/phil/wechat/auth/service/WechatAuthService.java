/**
 * FileName: AuthService
 * Author:   Phil
 * Date:     11/21/2018 12:10 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.service;

import com.phil.wechat.auth.model.BasicAuthParam;
import com.phil.wechat.auth.model.response.AuthAccessToken;
import com.phil.wechat.auth.model.response.AuthUserInfo;
import com.phil.modules.result.ResultState;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 12:10 PM
 * @since 1.0
 */
public interface WechatAuthService {

    /**
     * 获取授权凭证token
     *
     * @return json格式的字符串
     */
    String getAccessToken();

    /**
     * 获取授权请求url
     *
     * @param basic
     * @param url
     * @return
     * @throws Exception
     */
    String getAuthUrl(BasicAuthParam basic, String url);

    /**
     * 获取网页授权凭证
     *
     * @param basic
     * @param url
     * @return
     */
    AuthAccessToken getAuthAccessToken(BasicAuthParam basic, String url);

    /**
     * 刷新网页授权验证
     *
     * @param basic 参数
     * @param url   请求路径
     * @return 新的网页授权验证
     */
    AuthAccessToken refreshAuthAccessToken(BasicAuthParam basic, String url);

    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openid      用户唯一标识
     * @return 用户信息
     */
    AuthUserInfo getAuthUserInfo(String accessToken, String openid);

    /**
     * 检验授权凭证（access_token）是否有效
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openid      用户唯一标识
     * @return { "errcode":0,"errmsg":"ok"}表示成功 { "errcode":40003,"errmsg":"invalid
     * openid"}失败
     */
    ResultState authToken(String accessToken, String openid);
}
