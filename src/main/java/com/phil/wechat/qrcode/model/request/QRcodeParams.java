/**
 * FileName: QRcodeParams
 * Author:   Phil
 * Date:     2/5/2019 1:01 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.qrcode.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈参数,待改进〉
 * 〈〉
 *
 * @author Phil
 * @create 2/5/2019 1:01 PM
 * @since 1.0
 */
@Data
public class QRcodeParams implements Serializable {

    private String sceneStr;

    private int sceneId;

    private int expireSeconds;



}
