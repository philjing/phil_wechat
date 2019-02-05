/**
 * FileName: TextData
 * Author:   Phil
 * Date:     11/22/2018 2:49 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

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
 * @create 11/22/2018 2:49 PM
 * @since 1.0
 */

/**
 * touser : ["OPENID1","OPENID2"]
 * msgtype : text
 * text : {"content":"hello from boxer."}
 */
@Getter
@Setter
@ToString
public class MassUserTextData extends MassUserData<MassTextType> {

    private static final long serialVersionUID = -4751326454209709768L;

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
        MassUserTextData data = new MassUserTextData();
        data.addOpenid("2213123");
        data.addOpenid("123213");
        data.addType(new MassTextType("23123"));
        System.out.println(data.toJson());
    }
}
