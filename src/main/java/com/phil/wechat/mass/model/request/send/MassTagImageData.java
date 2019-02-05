/**
 * FileName: MassTagImageData
 * Author:   Phil
 * Date:     11/23/2018 7:34 PM
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
 * @create 11/23/2018 7:34 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class MassTagImageData extends MassTagData<MassImageType> {

    private static final long serialVersionUID = -3520887325414813037L;

    private MassImageType image;

    @Override
    public void addType(MassImageType image) {
        this.image = image;
    }

    @Override
    public String set() {
        return MassTypes.IMAGE;
    }
}
