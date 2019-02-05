/**
 * FileName: ResultState
 * Author:   Phil
 * Date:     8/2/2018 12:54
 * Description: 状态返回码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉
 * 〈状态返回码〉
 *
 * @author Phil
 * @create 8/2/2018 12:54
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class ResultState implements Serializable {

    private static final long serialVersionUID = -6184155678037435926L;
    
    private int errcode;

    private String errmsg;
}
