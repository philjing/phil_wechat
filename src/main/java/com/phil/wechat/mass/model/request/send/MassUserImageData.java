/**
 * FileName: ImageData
 * Author:   Phil
 * Date:     11/22/2018 1:10 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.phil.wechat.mass.model.request.MassTypes;
import com.phil.wechat.mass.model.request.type.MassImageType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/22/2018 1:10 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class MassUserImageData extends MassUserData<MassImageType> {

    private static final long serialVersionUID = 7071762163785028003L;

    private MassImageType image;

    @Override
    public void addType(MassImageType image) {
        this.image = image;
    }

    @Override
    public String set() {
        return MassTypes.IMAGE;
    }

    public static void main(String[] args) {
        MassUserImageData data = new MassUserImageData();
        data.addOpenid("123");
        data.addOpenid("3434");
        data.addType(new MassImageType(""));
        System.out.println(data.toJson());
    }
}
