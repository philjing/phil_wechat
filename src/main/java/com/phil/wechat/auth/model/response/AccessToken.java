/**
 * FileName: AccessToken
 * Author:   Phil
 * Date:     12/4/2018 2:13 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈微信通用接口凭证〉
 *
 * @author Phil
 * @create 12/4/2018 2:13 PM
 * @since 1.0
 */
@Getter
@Setter
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 5806078615354556552L;

    // 获取到的凭证
    @SerializedName("access_token")
    private String accessToken;

    // 凭证有效时间，单位：秒
    private int expires_in;

}
