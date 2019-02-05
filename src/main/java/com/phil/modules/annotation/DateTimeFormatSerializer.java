/**
 * FileName: DateTimeFormatSerializer
 * Author:   Phil
 * Date:     12/9/2018 10:28 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.annotation;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.phil.modules.util.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Phil
 * @create 12/9/2018 10:28 AM
 * @since 1.0
 */
public class DateTimeFormatSerializer implements JsonSerializer<Date> {


    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     *
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(FastDateFormat.getInstance(DateUtils.DATETIME_FORMAT).format(src));

    }
}
