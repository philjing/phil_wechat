/**
 * FileName: MassWxcardType
 * Author:   Phil
 * Date:     11/22/2018 3:19 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.phil.wechat.mass.model.request.MassTypes;
import com.phil.wechat.mass.model.request.type.MassWxcardType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/22/2018 3:19 PM
 * @since 1.0
 */

/**
 * touser : ["OPENID1","OPENID2"]
 * wxcard : {"card_id":"123dsdajkasd231jhksad"}
 * msgtype : wxcard
 */
@Getter
@Setter
@ToString
public class MassUserWxcardData extends MassUserData<MassWxcardType> {

    private static final long serialVersionUID = -3219111041714718156L;

    private MassWxcardType wxcard;

    @Override
    public void addType(MassWxcardType wxcard) {
        this.wxcard = wxcard;
    }

    @Override
    public String set() {
        return MassTypes.WXCARD;
    }
}
