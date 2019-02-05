/**
 * FileName: AuthCodeParam
 * Author:   Phil
 * Date:     12/4/2018 2:12 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.model.request;

import com.phil.modules.util.EncodeUtils;
import com.phil.wechat.auth.model.BasicAuthParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br> 
 * 〈授权code请求参数〉
 *
 * @author Phil
 * @create 12/4/2018 2:12 PM
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthCodeParam extends BasicAuthParam {

    private static final long serialVersionUID = 6313379843885615765L;

    public static final String SCOPE_SNSAPIBASE = "snsapi_base"; // snsapi_base(不需要弹出授权页面,只能获取openid)

    public static final String SCOPE_SNSPAIUSERINFO = "snsapi_userinfo"; // 弹出授权页面(获取用户基本信息)

    private String appid;

    private String redirect_uri; // 使用urlencode对链接进行处理

    private String response_type = "code";

    private String scope;

    private String state;

    /**
     * 参数组装
     *
     * @return
     */
    public Map<String, String> getParams() {
        Map<String, String> params = new TreeMap<>();
        params.put("appid", this.appid);
        params.put("redirect_uri", EncodeUtils.urlEncode(this.redirect_uri));
        params.put("response_type", this.response_type);
        params.put("scope", this.scope);
        params.put("state", this.state);
        return params;
    }


}
