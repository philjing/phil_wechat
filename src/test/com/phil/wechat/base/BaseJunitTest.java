/**
 * FileName: BaseJunitTest
 * Author:   Phil
 * Date:     12/23/2018 7:29 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.base;

import com.phil.modules.config.WechatProperties;
import com.phil.modules.config.WechatPropertiesConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 12/23/2018 7:29 PM
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-servlet.xml", "classpath:spring/spring-context.xml"})
public class BaseJunitTest {

    @Test
    public void testWechatProperties() {

        System.out.println(WechatProperties.getProperty(WechatPropertiesConstant.APP_ID));


    }


}
