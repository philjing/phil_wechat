/**
 * FileName: WechatMaterialService
 * Author:   Phil
 * Date:     11/23/2018 11:50 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.material.service;

import com.phil.modules.result.ResultState;
import com.phil.wechat.material.model.request.UploadNewsEntity;
import com.phil.wechat.material.model.response.GetMaterialResult;
import com.phil.wechat.material.model.response.MaterialCountResult;
import com.phil.wechat.material.model.response.MaterialResult;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/23/2018 11:50 PM
 * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738726
 * @since 1.0
 */
public interface WechatMaterialService {

    /**
     * 新增临时素材(本地)
     *
     * @param accessToken
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、(video)和缩略图（thumb）
     * @param filePath    本地路径
     * @return
     */
    MaterialResult uploadTempMediaFile(String accessToken, String type, String filePath);

    /**
     * 新增临时素材(网络)
     *
     * @param accessToken
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、(video)和缩略图（thumb）
     * @param url         网络路径
     * @return
     */
    MaterialResult uploadTempMediaUrl(String accessToken, String type, String url);

    /**
     * 新增永久素材(本地)
     *
     * @param accessToken
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @param filePath    本地路径
     * @return
     */
    MaterialResult uploadForeverMediaFile(String accessToken, String type, String filePath);

    /**
     * 新增永久素材(网络)
     *
     * @param accessToken
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @param url         网络路径
     * @return
     */
    MaterialResult uploadForeverMediaUrl(String accessToken, String type, String url);

    /**
     * 新增永久图文素材
     * 返回示例
     * {
     * "media_id":MEDIA_ID
     * }
     * 活得新增的图文消息素材的media_id
     *
     * @param accessToken
     * @param entity      图文消息article组合的data
     * @return
     * @see com.phil.wechat.mass.service.WechatMassService#uploadForMassNewsFile
     */
    String uploadNewsMedia(String accessToken, UploadNewsEntity entity);

    /**
     * {
     * "url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"
     * }
     * 图文消息的具体内容中，微信后台将过滤外部的图片链接，图片url需通过"上传图文消息内的图片获取URL"接口上传图片获取
     * 获取
     *
     * @param accessToken
     * @param filePath    本地路径
     * @return
     */
    String uploadFileForNewsMedia(String accessToken, String filePath);

    /**
     * {
     * "url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"
     * }
     * 图文消息的具体内容中，微信后台将过滤外部的图片链接，图片url需通过"上传图文消息内的图片获取URL"接口上传图片获取
     * 获取
     *
     * @param accessToken
     * @param url         网络路径
     * @return
     */
    String uploadUrlForNewsMedia(String accessToken, String url);

    /**
     * 上传本地video永久素材
     * 返回格式
     * {
     * "media_id":MEDIA_ID
     * }
     *
     * @param accessToken
     * @param title        视频素材的标题
     * @param introduction 视频素材的描述
     * @param filePath     本地路径
     * @return
     */
    String uploadForeverMediaFile(String accessToken, String title, String introduction, String filePath);

    /**
     * 上传网络video永久素材
     * 返回格式
     * {
     * "media_id":MEDIA_ID
     * }
     *
     * @param accessToken
     * @param title        视频素材的标题
     * @param introduction 视频素材的描述
     * @param url          网络路径
     * @return
     */
    String uploadForeverMediaUrl(String accessToken, String title, String introduction, String url);

    /**
     * 获取临时素材
     * POST格式
     * {
     * "media_id":MEDIA_ID
     * }
     * <p>
     *
     * @param accessToken
     * @param mediaId     要获取的素材的media_id
     * @return 返回Json(不做处理)
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738727
     */
    String getTempMaterial(String accessToken, String mediaId);

    /**
     * 获取永久素材
     * POST格式
     * {
     * "media_id":MEDIA_ID
     * }
     * <p>
     *
     * @param accessToken
     * @param mediaId     要获取的素材的media_id
     * @return 返回Json(不做处理)
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738730
     */
    GetMaterialResult getMaterial(String accessToken, String mediaId);

    /**
     * 删除永久素材
     * POST格式
     * {
     * "media_id":MEDIA_ID
     * }
     * <p>
     *
     * @param accessToken
     * @param mediaId     要获取的素材的media_id
     * @return 状态
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738731
     */
    ResultState deleteMaterial(String accessToken, String mediaId);

    /**
     * 获取素材总数
     *
     * @param accessToken
     * @return 状态
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738733
     */
    MaterialCountResult geMaterialCount(String accessToken);

    /**
     * 获取素材列表
     * POST格式
     *  {
     *  "type":TYPE,
     *  "offset":OFFSET,
     *  "count":COUNT
     * }
     *
     * @param accessToken
     * @return 状态
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738731
     */
    String batchGetMaterial(String accessToken);

}
