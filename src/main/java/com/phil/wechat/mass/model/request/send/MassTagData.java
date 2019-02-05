/**
 * FileName: MassTagData
 * Author:   Phil
 * Date:     11/23/2018 5:51 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.send;

import com.google.gson.annotations.SerializedName;
import com.phil.wechat.mass.model.request.MassData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/23/2018 5:51 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public abstract class MassTagData<T> extends MassData<T> {

    private static final long serialVersionUID = -8934093072295890913L;

    private Filter filter;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class Filter {
        /**
         * is_to_all : false
         * tag_id : 2
         */
        @SerializedName("is_to_all")
        private boolean toAll;

        @SerializedName("tag_id")
        private int tagId;
    }

    public void addFilter(Filter filter) {
        this.filter = filter;
    }
}

