/**
 * FileName: SignName
 * Author:   Phil
 * Date:     8/1/2018 13:50
 * Description: 签名
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.annotation;

import java.lang.annotation.*;

/**
 * 〈一句话功能简述〉
 * 〈签名〉
 *
 * @author Phil
 * @create 8/1/2018 13:50
 * @since 1.0.0
 */
@Documented
@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SignName {

    String value() default "";

    String key() default "";
}
