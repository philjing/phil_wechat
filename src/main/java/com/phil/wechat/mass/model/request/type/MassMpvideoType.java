/**
 * FileName: MpVideoItem
 * Author:   Phil
 * Date:     8/3/2018 21:38
 * Description: 视频素材的POST表单信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.model.request.type;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈群发视频素材的POST表单信息〉
 *
 * @author Phil
 * @ 8/3/2018 21:38
 * @s
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class MassMpvideoType  {

    @SerializedName("media_id")
    private String mediaId;

    private String title;

    private String description;
}
