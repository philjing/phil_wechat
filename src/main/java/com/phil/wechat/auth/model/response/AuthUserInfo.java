/**
 * FileName: AuthUserInfo
 * Author:   Phil
 * Date:     12/4/2018 2:15 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈通过网页授权获取的用户信息〉
 *
 * @author Phil
 * @create 12/4/2018 2:15 PM
 * @since 1.0
 */
@Getter
@Setter
public class AuthUserInfo implements Serializable {

    private static final long serialVersionUID = 5325219651225561359L;
    // 用户标识
    private String openid;
    // 用户昵称
    private String nickname;
    // 性别(1是男性,2是女性,0是未知)
    private String sex;
    // 国家
    private String country;
    // 省份
    private String province;
    // 城市
    private String city;
    // 用户头像链接
    private String headimgurl;
    // 用户特权信息
    private List<String> privilege;
    // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
    private String unionid;
}
