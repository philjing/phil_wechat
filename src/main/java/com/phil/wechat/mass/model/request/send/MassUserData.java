/**
 * FileName: MassUserData
 * Author:   Phil
 * Date:     11/23/2018 7:18 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.phil.wechat.mass.model.request.MassData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/23/2018 7:18 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public abstract class MassUserData<T> extends MassData<T> {

    private static final long serialVersionUID = -1487789544030118316L;

    private List<String> touser = new ArrayList<>();

    public void addOpenid(String openid) {
        this.touser.add(openid);
    }
}
