/**
 * FileName: AuthAccessToken
 * Author:   Phil
 * Date:     12/4/2018 2:14 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * 〈一句话功能简述〉<br>
 * 〈网页授权access_token〉
 *
 * @author Phil
 * @create 12/4/2018 2:14 PM
 * @since 1.0
 */
public class AuthAccessToken extends AccessToken {

    private static final long serialVersionUID = -525656415464372637L;

    @SerializedName("refresh_token")
    private String refreshToken; // 用户刷新access_token

    private String openid; // 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID

    private String scope; // 用户授权的作用域，使用逗号（,）分隔
}
