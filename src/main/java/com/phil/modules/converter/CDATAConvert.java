/**
 * FileName: XStreamCDataConverter
 * Author:   Phil
 * Date:     11/20/2018 4:04 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 〈一句话功能简述〉<br>
 * 处理JAVA对象转换成XML时添加<!CDATA[ ]]>标签
 *
 * @author Phil
 * @create 11/20/2018 4:04 PM
 * @since 1.0
 */
public class CDATAConvert implements Converter {

    /**
     * java对象转换为xml
     */
    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {

        writer.setValue("<![CDATA[" + object + "]]>");
    }

    /**
     * xml转换成JAVA对象
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return reader.getValue();
    }

    /**
     * 判断字段是否是需要转换的类型
     */
    @Override
    public boolean canConvert(Class paramClass) {
        return String.class.isAssignableFrom(paramClass);
    }
}