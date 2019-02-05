/**
 * FileName: RefreshAuthTokenParam
 * Author:   Phil
 * Date:     12/4/2018 2:09 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.model.request;

import com.phil.wechat.auth.model.BasicAuthParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br> 
 * 〈刷新token请求〉
 *
 * @author Phil
 * @create 12/4/2018 2:09 PM
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class RefreshAuthTokenParam extends BasicAuthParam {

    private static final long serialVersionUID = -1748872326563685295L;

    private String appid;

    private String grant_type = "refresh_token";

    private String refresh_token;

    /**
     * 参数组装
     *
     * @return
     */
    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new TreeMap<>();
        params.put("appid", this.appid);
        params.put("grant_type", this.grant_type);
        params.put("refresh_token", this.refresh_token);
        return params;
    }
}

