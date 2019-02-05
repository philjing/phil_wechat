/**
 * FileName: WechatMaterialServiceImpl
 * Author:   Phil
 * Date:     11/30/2018 4:33 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.material.service.impl;

import com.google.gson.JsonObject;
import com.phil.modules.config.WechatProperties;
import com.phil.modules.config.WechatPropertiesConstant;
import com.phil.modules.result.ResultState;
import com.phil.modules.util.HttpUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.mass.model.response.MassNewsResult;
import com.phil.wechat.mass.service.WechatMassService;
import com.phil.wechat.material.constant.WechatMaterialConstant;
import com.phil.wechat.material.model.request.UploadNewsEntity;
import com.phil.wechat.material.model.response.GetMaterialResult;
import com.phil.wechat.material.model.response.MaterialCountResult;
import com.phil.wechat.material.model.response.MaterialResult;
import com.phil.wechat.material.service.WechatMaterialService;
import com.phil.wechat.material.util.MaterialUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/30/2018 4:33 PM
 * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738726
 * @since 1.0
 */
@Service
@Slf4j
public class WechatMaterialServiceImpl implements WechatMaterialService {

    /**
     * 新增临时素材(本地)
     *
     * @param accessToken
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、(video)和缩略图（thumb）
     * @param filePath    本地路径
     * @return
     */
    @Override
    public MaterialResult uploadTempMediaFile(String accessToken, String type, String filePath) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("type", type);
        try {
            String result = MaterialUtil.uploadMediaFile(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_TEMP_MEDIA_URL), params, filePath);
            return JsonUtil.fromJson(result, MaterialResult.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 新增临时素材(网络)
     *
     * @param accessToken
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、(video)和缩略图（thumb）
     * @param url         网络路径
     * @return
     */
    @Override
    public MaterialResult uploadTempMediaUrl(String accessToken, String type, String url) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("type", type);
        try {
            String result = MaterialUtil.uploadMediaUrl(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_TEMP_MEDIA_URL), params, url);
            return JsonUtil.fromJson(result, MaterialResult.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 新增永久素材(本地)
     *
     * @param accessToken
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @param filePath    本地路径
     * @return
     */
    @Override
    public MaterialResult uploadForeverMediaFile(String accessToken, String type, String filePath) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("type", type);
        try {
            String result = MaterialUtil.uploadMediaFile(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_MATERIAL_URL), params, filePath);
            return JsonUtil.fromJson(result, MaterialResult.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 新增永久素材(网络)
     *
     * @param accessToken
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @param url         网络路径
     * @return
     */
    @Override
    public MaterialResult uploadForeverMediaUrl(String accessToken, String type, String url) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("type", type);
        try {
            String result = MaterialUtil.uploadMediaUrl(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_MATERIAL_URL), params, url);
            return JsonUtil.fromJson(result, MaterialResult.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

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
     * @see WechatMassService#uploadForMassNewsFile
     */
    @Override
    public String uploadNewsMedia(String accessToken, UploadNewsEntity entity) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String data = JsonUtil.toJson(entity);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.ADD_NEWS_URL), params, data);
        return JsonUtil.fromJson(result, MassNewsResult.class).getMediaId();
    }

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
    @Override
    public String uploadFileForNewsMedia(String accessToken, String filePath) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        try {
            String result = MaterialUtil.uploadMediaFile(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_IMG_MEDIA_URL), params, filePath);
            return JsonUtil.fromJson(result, MaterialResult.class).getUrl();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

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
    @Override
    public String uploadUrlForNewsMedia(String accessToken, String url) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        try {
            String result = MaterialUtil.uploadMediaUrl(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_IMG_MEDIA_URL), params, url);
            return JsonUtil.fromJson(result, MaterialResult.class).getUrl();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

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
    @Override
    public String uploadForeverMediaFile(String accessToken, String title, String introduction, String filePath) {
        //只能是mp4的
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("type", WechatMaterialConstant.MATERIAL_UPLOAD_TYPE_VIDEO);
        try {
            String result = MaterialUtil.uploadVideoMediaFile(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_MATERIAL_URL),
                    params, filePath, title, introduction);
            return JsonUtil.fromJson(result, MaterialResult.class).getMediaId();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

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
    @Override
    public String uploadForeverMediaUrl(String accessToken, String title, String introduction, String url) {
        //只能是mp4的
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("type", WechatMaterialConstant.MATERIAL_UPLOAD_TYPE_VIDEO);
        try {
            String result = MaterialUtil.uploadVideoMediaUrl(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_MATERIAL_URL),
                    params, url, title, introduction);
//            log.error(result);
            return JsonUtil.fromJson(result, MaterialResult.class).getMediaId();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取临时素材
     * POSt格式
     * {
     * "media_id":MEDIA_ID
     * }
     *
     * @param accessToken
     * @param mediaId     要获取的素材的media_id
     * @return 返回Json(不做处理)
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738727
     */
    @Override
    public String getTempMaterial(String accessToken, String mediaId) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("media_id", mediaId);
        return HttpUtil.doGet(WechatProperties.getProperty(WechatPropertiesConstant.GET_TEMP_MEDIA_URL), params);
    }

    /**
     * 获取永久素材
     * POSt格式
     * {
     * "media_id":MEDIA_ID
     * }
     *
     * @param accessToken
     * @param mediaId     要获取的素材的media_id
     * @return 返回Json(不做处理) 图片暂时未能正确处理
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738730
     */
    @Override
    public GetMaterialResult getMaterial(String accessToken, String mediaId) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        JsonObject data = new JsonObject();
        data.addProperty("media_id", mediaId);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.GET_MATERIAL_URL), params, data.toString());
        return JsonUtil.fromJson(result, GetMaterialResult.class);
    }

    /**
     * 删除永久素材
     * POSt格式
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
    @Override
    public ResultState deleteMaterial(String accessToken, String mediaId) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        JsonObject data = new JsonObject();
        data.addProperty("media_id", mediaId);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.DELETE_MATERIAL_URL), params, data.toString());
        return JsonUtil.fromJson(result, ResultState.class);
    }

    /**
     * 获取素材总数
     *
     * @param accessToken
     * @return 状态
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738733
     */
    @Override
    public MaterialCountResult geMaterialCount(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String result = HttpUtil.doGet(WechatProperties.getProperty(WechatPropertiesConstant.GET_MATERIAL_COUNT_URL), params);
        return JsonUtil.fromJson(result, MaterialCountResult.class);
    }

    /**
     * 获取素材列表
     * POST格式
     * {
     * "type":TYPE,
     * "offset":OFFSET,
     * "count":COUNT
     * }
     *
     * @param accessToken
     * @return 状态
     * @link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738731
     */
    @Override
    public String batchGetMaterial(String accessToken) {
        return null;
    }
}
