/**
 * FileName: WechatProperties
 * Author:   Phil
 * Date:     12/23/2018 12:10 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 12/23/2018 12:10 AM
 * @since 1.0
 */
@Slf4j
public class WechatProperties extends PropertyPlaceholderConfigurer {

    private static Map<String, String> propertiesMap;

    private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

    @Override
    public void setSystemPropertiesMode(int systemPropertiesMode) {
        super.setSystemPropertiesMode(systemPropertiesMode);
        springSystemPropertiesMode = systemPropertiesMode;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        propertiesMap = new HashMap<>();
        log.info("loading" + props.keySet().size() + "keys");
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String valueStr = resolvePlaceholder(keyStr, props, springSystemPropertiesMode);
            propertiesMap.put(keyStr, valueStr);
        }
        System.out.println(propertiesMap);
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name);
    }

}
