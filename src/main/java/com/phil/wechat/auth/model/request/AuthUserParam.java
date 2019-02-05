/**
 * FileName: AuthUserParam
 * Author:   Phil
 * Date:     12/4/2018 2:11 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.model.request;

import com.phil.wechat.auth.model.BasicAuthParam;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户信息请求〉
 *
 * @author Phil
 * @create 12/4/2018 2:11 PM
 * @since 1.0
 */
@Getter
@Setter
public class AuthUserParam extends BasicAuthParam {

    private static final long serialVersionUID = 66535717787322321L;

    private String accessToken;

    private String openid;

    private String lang;

    /**
     * 参数组装
     * @return
     */
    public Map<String, String> getParams() {
        Map<String, String> params = new TreeMap<>();
        params.put("access_token", this.accessToken);
        params.put("openid", this.openid);
        params.put("lang", this.lang);
        return params;
    }
}

