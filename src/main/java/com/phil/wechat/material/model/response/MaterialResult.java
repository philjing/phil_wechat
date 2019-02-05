/**
 * FileName: MeterResult
 * Author:   Phil
 * Date:     8/3/2018 21:35
 * Description: 素材返回结果
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.material.model.response;

import com.phil.wechat.mass.model.response.MassNewsResult;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈素材返回结果〉
 *
 * @author Phil
 * @create 8/3/2018 21:35
 * @since 1.0.0
 */
@Getter
@Setter
public class MaterialResult extends MassNewsResult {

    private static final long serialVersionUID = 4014347682266386223L;

//    private String type; // 上传的媒体类型
//
//    @SerializedName("media_id")
//    private String mediaId; // 媒体文件上传后，获取时的唯一标识
//
//    @SerializedName("created_at")
//    private long createdAt; // 媒体上传成功的时间戳
//
//    private String url;  //新增的图片素材的图片URL

}
