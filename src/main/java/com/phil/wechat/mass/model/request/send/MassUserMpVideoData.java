/**
 * FileName: MpVideoItem
 * Author:   Phil
 * Date:     8/3/2018 21:38
 * Description: 视频素材的POST表单信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.phil.wechat.mass.model.request.MassTypes;
import com.phil.wechat.mass.model.request.type.MassMpvideoType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈群发视频素材的POST表单信息〉
 *
 * @author Phil
 * @ 8/3/2018 21:38
 * @since 1.0
 */

/**
 * touser : ["OPENID1","OPENID2"]
 * mpvideo : {"media_id":"123dsdajkasd231jhksad","title":"TITLE","description":"DESCRIPTION"}
 * msgtype : mpvideo
 */
@Getter
@Setter
@ToString
public class MassUserMpVideoData extends MassUserData<MassMpvideoType> {

    private static final long serialVersionUID = -7017752845139526088L;

    private MassMpvideoType mpvideo;

    @Override
    public void addType(MassMpvideoType mpvideo) {
        this.mpvideo = mpvideo;
    }

    @Override
    public String set() {
        return MassTypes.MPVIDEO;
    }


}
