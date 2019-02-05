/**
 * FileName: MaterialCountResult
 * Author:   Phil
 * Date:     12/3/2018 12:16 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.material.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈获取素材总数返回结果〉
 *
 * @author Phil
 * @create 12/3/2018 12:16 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class MaterialCountResult implements Serializable {

    private static final long serialVersionUID = 7449909499376970976L;
    /**
     * voice_count : COUNT
     * video_count : COUNT
     * image_count : COUNT
     * news_count : COUNT
     */
    @SerializedName("voice_count")
    private int voiceCount;

    @SerializedName("video_count")
    private int videoCount;

    @SerializedName("image_count")
    private int imageCount;

    @SerializedName("news_count")
    private int newsCount;
}
