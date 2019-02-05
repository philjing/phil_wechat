/**
 * FileName: MassMsgResult
 * Author:   Phil
 * Date:     11/21/2018 8:23 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈查询群发消息发送状态返回的消息〉
 *
 * @author Phil
 * @create 11/21/2018 8:23 PM
 * @since 1.0
 */
@Getter
@Setter
public class MassMsgStatusResult implements Serializable {

    private static final long serialVersionUID = -4063626463329204601L;

    @SerializedName("msg_id")
    private int msgId;

    @SerializedName("msg_status")
    private String msgStatus;

}
