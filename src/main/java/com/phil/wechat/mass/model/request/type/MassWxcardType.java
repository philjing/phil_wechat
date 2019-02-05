/**
 * FileName: MassWxcardType
 * Author:   Phil
 * Date:     11/22/2018 3:19 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.type;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/22/2018 3:19 PM
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class MassWxcardType {

    @SerializedName("card_id")
    private String cardId;

}
