/**
 * FileName: MassNewsResult
 * Author:   Phil
 * Date:     11/21/2018 10:27 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈上传图文消息素材返回的结果〉
 *
 * @author Phil
 * @create 11/21/2018 10:27 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class MassNewsResult implements Serializable {

    private static final long serialVersionUID = -3662669732765426932L;

    private String type; // 上传的媒体类型

    @SerializedName("media_id")
    private String mediaId; // 媒体文件上传后，获取时的唯一标识

    @SerializedName("created_at")
    private long createdAt; // 媒体上传成功的时间戳

    private String url;  //新增的图片素材的图片URL
}
