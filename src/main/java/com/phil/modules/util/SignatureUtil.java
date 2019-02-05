/**
 * FileName: SignatureUtil
 * Author:   Phil
 * Date:     8/1/2018 12:32
 * Description: Signature Util
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.util;

import com.phil.modules.annotation.SignName;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈Signature Util〉
 *
 * @author Phil
 * @create 8/1/2018 12:32
 * @since 1.0.0
 */
@Slf4j
public class SignatureUtil {

    private SignatureUtil() {

    }

    /**
     * SHA1加密 验证签名
     *
     * @param signature 微信签名
     * @param params    token,timestamp,nonce
     * @return 是否符合
     */
    public static boolean checkSignature(String signature, String... params) {
        Arrays.sort(params);
        String str = StringUtils.join(params);
        String sign = DigestUtils.sha1Hex(str);
        return Objects.equals(signature, sign);
    }

    /**
     * @param obj 要参与签名的Clas
     * @return
     */
    public static String md5(Object obj) {
        String str = notSignParams(obj, null, null);
        return DigestUtils.md5Hex(str);
    }

    /**
     * @param obj     要参与签名的Clas
     * @param keys    参数
     * @param ignores 忽略的
     * @return
     */
    public static String md5(Object obj, String keys, String[] ignores) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", keys);
        String str = notSignParams(obj, map, ignores);
        return DigestUtils.md5Hex(str);
    }

    /**
     * @param obj     要参与签名的Clas
     * @param keys    参数
     * @param ignores 忽略的
     * @return
     */
    public static String md5(Object obj, Map<String, Object> keys, String[] ignores) {
        String str = notSignParams(obj, keys, ignores);
        return DigestUtils.md5Hex(str);
    }

    /**
     * @param map     要参与签名的map
     * @param keys    参数
     * @param ignores 忽略的
     * @return 签名
     */
    public static String sha1Hex(Map<String, Object> map, Map<String, Object> keys, String[] ignores) {
        String str = notSignParams(map, keys, ignores);
        log.info(str);
        return DigestUtils.sha1Hex(str.getBytes());
    }


    public static String SHA1(Map<String, Object> map) {
        try {
            String decript = notSignParams(map, null, null);
            log.info(decript);
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes(StandardCharsets.UTF_8));
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte message : messageDigest) {
                String shaHex = Integer.toHexString(message & 0xFF);
                if (shaHex.length() < 2)
                    hexString.append(0);
                hexString.append(shaHex);
            }
            return hexString.toString().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 从API返回的XML数据封装的Map重新计算一次签名
     *
     * @param map     要参与签名的map
     * @param keys    参数
     * @param ignores 忽略的
     * @return
     */
    private static String reSign(Map<String, Object> map, Map<String, Object> keys, String[] ignores) {
        // 清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        // map.put("sign", "");
        if (ignores != null) {
            for (String s : ignores) {
                map.put(s, "");
            }
        }
        return md5(map, keys, ignores);
    }

    /**
     * 检验API返回的数据里面的签名是否合法,规则是:按参数名称a-z排序,遇到空值的参数不参加签名
     *
     * @param apiXml API返回的XML数据字符串
     * @param apiKey Key
     * @return API签名是否有效
     */
    public static boolean checkValidPaySign(String apiXml, String apiKey) {
        Map<String, Object> map = null;
        try {
            map = XmlUtil.toMap(apiXml);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.error(apiXml + "转换出错了");
            return false;
        }
        String apiSign = (String) map.get("sign");
        if (StringUtils.isEmpty(apiXml)) {
            log.error("API返回的数据签名数据不存在");
            return false;
        }
        // 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        Map<String, Object> keys = new HashMap<>();
        keys.put("key", apiKey);
        String reSign = reSign(map, keys, new String[]{"sign"});
        if (!Objects.equals(apiSign, reSign)) {
            log.error("API返回的数据签名验证不通过");
            return false;
        }
        log.debug("API返回的数据签名验证通过");
        return true;
    }

    /**
     * 返回未加密的字符串
     *
     * @param obj     要参与签名的Clas
     * @param keys    参数
     * @param ignores 忽略的
     * @return 签名
     */
    private static String notSignParams(Object obj, Map<String, Object> keys, String[] ignores) {
        StringBuilder result = new StringBuilder(StringUtils.EMPTY);
        try {
            Class<?> cls = obj.getClass();
            Field[] fields = cls.getDeclaredFields();
            List<String> list = getFieldList(fields, obj, ignores);
            Field[] superFields = cls.getSuperclass().getDeclaredFields(); // 获取父类的私有属性
            list.addAll(getFieldList(superFields, obj, ignores));
            int size = list.size();
            if (size <= 0) {
                return result.toString();
            }
            String[] arrayToSort = list.toArray(new String[size]);
            Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER); // 严格按字母表顺序排序
            for (String item : arrayToSort) {
                result.append(item);
            }
            if (keys != null && keys.size() > 0) {
                for (Map.Entry<String, Object> entry : keys.entrySet()) {
                    result.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }
            result = new StringBuilder(result.substring(0, result.lastIndexOf("&")));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result.toString();
    }

    /**
     * 返回未加密的字符串
     *
     * @param keys    参数
     * @param ignores 忽略的
     * @return 待加密的字符串
     */
    private static String notSignParams(Map<String, Object> params, Map<String, Object> keys, String[] ignores) {
        StringBuilder result = new StringBuilder();
        List<String> list = new LinkedList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != "" && entry.getValue() != null) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        if (size <= 0) {
            return result.toString();
        }
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);// 严格按字母表顺序排序
        for (String item : arrayToSort) {
            result.append(item);
        }
        if (keys != null && keys.size() > 0) {
            for (Map.Entry<String, Object> entry : keys.entrySet()) {
                result.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return result.substring(0, result.lastIndexOf("&"));
    }


    /**
     * 将字段集合方法转换
     *
     * @param fileds
     * @param object
     * @param ignores 过滤 sign、serialVersionUID
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static List<String> getFieldList(Field[] fileds, Object object, String[] ignores)
            throws IllegalArgumentException, IllegalAccessException {
        List<String> list = new LinkedList<>();
        for (Field f : fileds) {
            if (ArrayUtils.contains(ignores, f.getName())) {
                continue;
            }
            f.setAccessible(true);
            if (f.isAnnotationPresent(SignName.class)) {
                SignName name = f.getAnnotation(SignName.class);
                list.add(name.key() + "=" + f.get(object) + "&");
            } else {
                list.add(f.getName() + "=" + f.get(object) + "&");
            }
        }
        return list;
    }
}
