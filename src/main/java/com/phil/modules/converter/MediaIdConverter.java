/**
 * FileName: MediaIdConverter
 * Author:   Phil
 * Date:     11/20/2018 6:27 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.converter;

import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/20/2018 6:27 PM
 * @since 1.0
 */
public class MediaIdConverter extends StringConverter {

    @Override
    public String toString(Object obj) {
        return "<MediaId>" + super.toString(obj) + "</MediaId>";
    }
}
