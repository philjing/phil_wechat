/**
 * FileName: GetMaterialResult
 * Author:   Phil
 * Date:     12/3/2018 12:19 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.material.model.response;

import com.google.gson.annotations.SerializedName;
import com.phil.wechat.material.model.request.UploadNewsEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 12/3/2018 12:19 PM
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class GetMaterialResult implements Serializable {

    private static final long serialVersionUID = 2061635546372015184L;

    @SerializedName("news_item")
    private List<UploadNewsEntity.UploadNewsArticle> items;

    /**
     * title : TITLE
     * description : DESCRIPTION
     * down_url : DOWN_URL
     */
    private String title;

    private String description;

    @SerializedName("down_url")
    private String downUrl;
}
