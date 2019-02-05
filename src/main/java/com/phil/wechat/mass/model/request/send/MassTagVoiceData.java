/**
 * FileName: MassTagVoiceData
 * Author:   Phil
 * Date:     11/23/2018 11:27 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.phil.wechat.mass.model.request.MassTypes;
import com.phil.wechat.mass.model.request.type.MassVoiceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/23/2018 11:27 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class MassTagVoiceData extends MassTagData<MassVoiceType> {

    private static final long serialVersionUID = -13757655142202287L;

    private MassVoiceType voice;

    @Override
    public void addType(MassVoiceType voice) {
        this.voice = voice;
    }

    @Override
    public String set() {
        return MassTypes.VOICE;
    }
}
