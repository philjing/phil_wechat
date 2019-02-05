/**
 * FileName: MassMpnewsType
 * Author:   Phil
 * Date:     11/22/2018 3:04 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.google.gson.annotations.SerializedName;
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
 * @create 11/22/2018 3:04 PM
 * @since 1.0
 */

/**
 * mpnews : {"media_id":"123dsdajkasd231jhksad"}
 * msgtype : mpnews
 * send_ignore_reprint : 0
 */
@Getter
@Setter
@ToString
public class MassUserMpnewsData extends MassUserData<MassMpnewsType> {

    private static final long serialVersionUID = 6415789540393638526L;

    private MassMpnewsType mpnews;

    @SerializedName("send_ignore_reprint")
    private int sendIgnoreReprint;

    @Override
    public void addType(MassMpnewsType mpnews) {
        this.mpnews = mpnews;
    }

    @Override
    public String set() {
        return MassTypes.MPNEWS;
    }
}
