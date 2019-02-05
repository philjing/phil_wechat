/**
 * FileName: MassTagWxcardData
 * Author:   Phil
 * Date:     11/23/2018 7:47 PM
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
 * @create 11/23/2018 7:47 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class MassTagWxcardData extends MassTagData<MassWxcardType> {

    private static final long serialVersionUID = 2309134494288127133L;

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
