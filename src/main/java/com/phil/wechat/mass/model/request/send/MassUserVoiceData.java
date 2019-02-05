/**
 * FileName: VoiceData
 * Author:   Phil
 * Date:     11/22/2018 2:36 PM
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
 * @create 11/22/2018 2:36 PM
 * @since 1.0
 */

/**
 * touser : ["OPENID1","OPENID2"]
 * voice : {"media_id":"mLxl6paC7z2Tl-NJT64yzJve8T9c8u9K2x-Ai6Ujd4lIH9IBuF6-2r66mamn_gIT"}
 * msgtype : voice
 */
@Getter
@Setter
@ToString
public class MassUserVoiceData extends MassUserData<MassVoiceType> {

    private static final long serialVersionUID = -7867854880425340450L;

    private MassVoiceType voice;

    @Override
    public void addType(MassVoiceType voice) {
        this.voice = voice;
    }

    @Override
    public String set() {
        return MassTypes.VOICE;
    }

    public static void main(String[] args) {
        MassUserVoiceData data = new MassUserVoiceData();
        data.addOpenid("213213");
        data.addOpenid("23213213");
        data.addType(new MassVoiceType("23213"));
        System.out.println(data.toJson());
    }
}
