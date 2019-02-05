/**
 * FileName: XmlUtil
 * Author:   Phil
 * Date:     8/1/2018 11:01
 * Description: XML Parse Util
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈XML Parse Util〉
 *
 * @author Phil
 * @create 8/1/2018
 * @since 1.0.0
 */
public class XmlUtil {

    private XmlUtil() {
    }

    /**
     * @return
     * @Description 为每次调用生成一个XStream
     * @Title getInstance
     */
    private static XStream getInstance() {
        XStream xStream = new XStream(new DomDriver(StandardCharsets.UTF_8.name())) {
            /**
             * 忽略xml中多余字段
             */
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        // 设置默认的安全校验
        XStream.setupDefaultSecurity(xStream);
        // 使用本地的类加载器
        xStream.setClassLoader(XmlUtil.class.getClassLoader());
        // 允许所有的类进行转换
        xStream.addPermission(AnyTypePermission.ANY);
        return xStream;
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public static Map<String, String> toMap(HttpServletRequest request) throws IOException {
        if (request != null && request.getInputStream() != null) {
            return toMap(request.getInputStream());
        }
        return new HashMap<>();
    }

    /**
     * @param inputStream
     * @return
     */
    public static Map<String, String> toMap(InputStream inputStream) {
        Map<String, String> map = new HashMap<>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 使用dom4将xml文件中的数据转换成Map<Object,Object>
     *
     * @param xml xml格式的字符串
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static Map<String, Object> toMap(String xml)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = IOUtils.toInputStream(xml, StandardCharsets.UTF_8);
        org.w3c.dom.Document document = builder.parse(is);
        // 获取到document里面的全部结点
        org.w3c.dom.NodeList allNodes = document.getFirstChild().getChildNodes();
        org.w3c.dom.Node node;
        Map<String, Object> map = new LinkedHashMap<>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof org.w3c.dom.Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }


    /**
     * 示例 <xml> <return_code><![CDATA[SUCCESS]]></return_code>
     * <return_msg><![CDATA[OK]]></return_msg>
     * <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     * <mch_id><![CDATA[10000100]]></mch_id>
     * <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
     * <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>
     * <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
     * <result_code><![CDATA[SUCCESS]]></result_code>
     * <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
     * <trade_type><![CDATA[JSAPI]]></trade_type> </xml>
     * 将xml数据(<![CDATA[SUCCESS]]>格式)映射到java对象中
     *
     * @param xml   待转换的xml格式的数据
     * @param clazz 待转换为的java对象
     * @return
     */
    public static <T> T fromXml(String xml, Class<T> clazz) {
//        // 将从API返回的XML数据映射到Java对象
//        XStream xStream = new XStream(new DomDriver(StandardCharsets.UTF_8.name(), new XmlFriendlyNameCoder("-_", "_")));//解决下划线问题
//        XStream.setupDefaultSecurity(xStream);
//        xStream.allowTypes(new Class[]{t});
//        xStream.alias("xml", t);
////        xStream.processAnnotations(t);
//        xStream.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
//        return (T) xStream.fromXML(xml);
        XStream xStream = getInstance();
        xStream.processAnnotations(clazz);
        Object object = xStream.fromXML(xml);
        return clazz.cast(object);

    }

    /**
     * 将java对象可转换为xml(<![CDATA[ ]]>格式)
     *
     * @param object
     * @return
     */
    public static String toXml(Object object) {
//        XStream xStream = new XStream(new DomDriver(StandardCharsets.UTF_8.name(), new XmlFriendlyNameCoder("-_", "_")));//解决下划线问题
////        XStream.setupDefaultSecurity(xStream);
//        //xstream使用注解转换
//        xStream.processAnnotations(obj.getClass());

        XStream xStream = getInstance();
        xStream.processAnnotations(object.getClass());
        // 剔除所有tab、制表符、换行符
//        return xStream.toXML(object).replaceAll("\\s+", " ");
        return StringEscapeUtils.unescapeXml(xStream.toXML(object));
    }
}
