/**
 * FileName: TemplateIdResult
 * Author:   Phil
 * Date:     11/29/2018 11:42 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.template.response;

import com.google.gson.annotations.SerializedName;
import com.phil.modules.result.ResultState;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈获得模板ID返回的消息〉
 *
 * @author Phil
 * @create 11/29/2018 11:42 PM
 * @since 1.0
 */
@Getter
@Setter
public class TemplateIdResult extends ResultState {

    private static final long serialVersionUID = -4415432676472671532L;

    @SerializedName("template_id")
    private String templateId;

}
