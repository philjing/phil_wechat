/**
 * FileName: MassData
 * Author:   Phil
 * Date:     11/24/2018 12:06 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request;

import com.phil.modules.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Phil
 * @create 11/24/2018 12:06 AM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public abstract class MassData<T> implements Serializable {

    private static final long serialVersionUID = 5661855842755598868L;

    private String msgtype = set();

    public abstract void addType(T t);

    public abstract String set();

    public String toJson(){
        return JsonUtil.toJson(this);
    }
}
