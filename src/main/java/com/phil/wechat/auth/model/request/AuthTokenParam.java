/**
 * FileName: AuthTokenParam
 * Author:   Phil
 * Date:     12/4/2018 2:11 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.model.request;

import com.phil.wechat.auth.model.BasicAuthParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br> 
 * 〈授权请求token的请求参数〉
 *
 * @author Phil
 * @create 12/4/2018 2:11 PM
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenParam extends BasicAuthParam {

    private static final long serialVersionUID = 4652953400751046159L;

    private String appid; //公众号的唯一标识

    private String secret; //公众号的appsecret

    private String code; //填写第一步获取的code参数

    private String grant_type = "authorization_code";

    /**
     * 参数组装
     *
     * @return
     */
    public Map<String, String> getParams() {
        Map<String, String> params = new TreeMap<>();
        params.put("appid", this.appid);
        params.put("secret", this.secret);
        params.put("code", this.code);
        params.put("grant_type", this.grant_type);
        return params;
    }
}

