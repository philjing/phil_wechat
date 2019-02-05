/**
 * FileName: MassTagNewsData
 * Author:   Phil
 * Date:     11/23/2018 7:47 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.phil.wechat.mass.model.request.MassTypes;
import com.phil.wechat.mass.model.request.type.MassMpnewsType;
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
public class MassTagMpnewsData extends MassTagData<MassMpnewsType> {

    private static final long serialVersionUID = -7166692641146830327L;

    private MassMpnewsType mpnews;

    @Override
    public void addType(MassMpnewsType mpnews) {
        this.mpnews = mpnews;
    }

    @Override
    public String set() {
        return MassTypes.MPNEWS;
    }
}
