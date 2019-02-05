/**
 * FileName: WechatMassService
 * Author:   Phil
 * Date:     11/21/2018 10:32 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.service;

import com.phil.modules.result.ResultState;
import com.phil.wechat.mass.model.request.MassNews;
import com.phil.wechat.mass.model.request.send.MassTagData;
import com.phil.wechat.mass.model.request.send.MassUserData;
import com.phil.wechat.mass.model.response.MassMsgResult;
import com.phil.wechat.mass.model.response.MassMsgStatusResult;

import java.util.Map;

/**
 * 〈群发相关〉<br>
 *
 * @author Phil
 * @create 11/21/2018 10:32 PM
 * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1481187827_i0l21
 * @since 1.0
 */
public interface WechatMassService {

    /**
     * 获取到图文消息素材里的thumb_media_id
     *
     * @param accessToken
     * @param type        类型
     * @param filePath    图片的本地路径
     * @return
     */
    String uploadForMassNewsFile(String accessToken, String type, String filePath);

    /**
     * 上传图文消息素材得到media_id
     *
     * @param accessToken
     * @param entity
     * @return
     * @see com.phil.wechat.material.service.WechatMaterialService#uploadNewsMedia
     */
    String uploadMassNews(String accessToken, MassNews entity);

    /**
     * 根据OpenId进行群发消息
     *
     * @param accessToken
     * @param massData
     * @return
     */
    MassMsgResult sendToOpenid(String accessToken, MassUserData massData);

    /**
     * 根据Tag进行群发消息
     *
     * @param accessToken
     * @param massData
     * @return
     */
    MassMsgResult sendToTag(String accessToken, MassTagData massData);

    /**
     * 根据OpenId进行群发消息预览
     *
     * @param accessToken
     * @param massData
     * @return
     */
    MassMsgResult previewToOpenid(String accessToken, Map<String, String> massData);

    /**
     * 删除群发
     *
     * @param accessToken
     * @param msgId       发送出去的消息ID
     * @param articleIdx  要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @return
     */
    ResultState deleteMassMessage(String accessToken, String msgId, int articleIdx);

    /**
     * 查询群发消息发送状态
     *
     * @param accessToken
     * @param msgId       发送出去的消息ID
     * @return
     */
    MassMsgStatusResult getMassMessageStatus(String accessToken, String msgId);

}
