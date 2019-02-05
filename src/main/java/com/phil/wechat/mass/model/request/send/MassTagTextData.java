/**
 * FileName: MassTagTextData
 * Author:   Phil
 * Date:     11/23/2018 7:21 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.phil.modules.util.JsonUtil;
import com.phil.wechat.mass.model.request.MassTypes;
import com.phil.wechat.mass.model.request.type.MassTextType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/23/2018 7:21 PM
 * @since 1.0
 */

/**
 * filter : {"is_to_all":false,"tag_id":2}
 * text : {"content":"CONTENT"}
 * msgtype : text
 */
@Getter
@Setter
@ToString
public class MassTagTextData extends MassTagData<MassTextType> {

    private static final long serialVersionUID = -1475706199386238857L;

    private MassTextType text;

    @Override
    public void addType(MassTextType text) {
        this.text = text;
    }

    @Override
    public String set() {
        return MassTypes.TEXT;
    }

    public static void main(String[] args) {
        MassTagTextData text = new MassTagTextData();
        text.addFilter(new Filter(false, 2));
        text.addType(new MassTextType("cen"));
        System.out.println(JsonUtil.toJson(text));
    }
}
