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
import com.phil.modules.result.ResultState;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈群发返回的消息〉
 *
 * @author Phil
 * @create 11/21/2018 8:23 PM
 * @since 1.0
 */
@Getter
@Setter
public class MassMsgResult extends ResultState {

    private static final long serialVersionUID = 2878274288571195983L;

    private String type; //媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息

    @SerializedName("msg_id")
    private long msgId;

    /**
     * 预览接口不会出现
     **/
    @SerializedName("msg_data_id")
    private long msgDataId;

}
