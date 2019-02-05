/**
 * FileName: MassTagMpvideoData
 * Author:   Phil
 * Date:     11/23/2018 7:48 PM
 * Description:
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
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/23/2018 7:48 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class MassTagMpvideoData extends MassTagData<MassMpvideoType> {

    private static final long serialVersionUID = 8825199243519207070L;

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
