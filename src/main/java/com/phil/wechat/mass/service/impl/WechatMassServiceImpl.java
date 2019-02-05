/**
 * FileName: WechatMassServiceImpl
 * Author:   Phil
 * Date:     11/21/2018 11:08 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.service.impl;

import com.google.gson.JsonObject;
import com.phil.modules.config.WechatProperties;
import com.phil.modules.config.WechatPropertiesConstant;
import com.phil.modules.util.HttpUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.modules.result.ResultState;
import com.phil.wechat.mass.model.request.MassNews;
import com.phil.wechat.mass.model.request.send.MassTagData;
import com.phil.wechat.mass.model.request.send.MassUserData;
import com.phil.wechat.mass.model.response.MassMsgResult;
import com.phil.wechat.mass.model.response.MassMsgStatusResult;
import com.phil.wechat.mass.model.response.MassNewsResult;
import com.phil.wechat.mass.service.WechatMassService;
import com.phil.wechat.material.util.MaterialUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 11:08 PM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatMassServiceImpl implements WechatMassService {

    /**
     * 获取到图文消息素材里的thumb_media_id
     *
     * @param accessToken
     * @param type        类型
     * @param filePath    图片的本地路径
     * @return
     */
    @Override
    public String uploadForMassNewsFile(String accessToken, String type, String filePath) {
        Map<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        params.put("type", type);
        try {
            String result = MaterialUtil.uploadMediaFile(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_IMG_MEDIA_URL), params, filePath);
            return JsonUtil.fromJson(result, MassNewsResult.class).getMediaId();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 上传图文消息素材得到media_id
     *
     * @param accessToken
     * @param entity
     * @return
     * @see com.phil.wechat.material.service.impl.WechatMaterialServiceImpl#uploadNewsMedia
     */
    @Override
    public String uploadMassNews(String accessToken, MassNews entity) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String data = JsonUtil.toJson(entity);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.UPLOAD_NEWS_URL), params, data);
        return JsonUtil.fromJson(result, MassNewsResult.class).getMediaId();
    }

    /**
     * 根据OpenId进行群发消息
     *
     * @param accessToken
     * @param massData
     * @return
     */
    @Override
    public MassMsgResult sendToOpenid(String accessToken, MassUserData massData) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        // post 提交的参数
//        String data = JsonUtil.toJson(massData);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.SEND_MASS_MESSAGE_URL), params, massData.toJson());
        return JsonUtil.fromJson(result, MassMsgResult.class);
    }

    /**
     * 根据Tag进行群发消息
     *
     * @param accessToken
     * @param massData
     * @return
     */
    @Override
    public MassMsgResult sendToTag(String accessToken, MassTagData massData) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.SEND_ALL_MASS_MESSAGE_URL), params, massData.toJson());
        return JsonUtil.fromJson(result, MassMsgResult.class);
    }

    /*
     * 有bug!!!!
     */

    /**
     * 根据OpenId进行群发消息预览
     *
     * @param accessToken
     * @param massData
     * @return
     */
    @Override
    public MassMsgResult previewToOpenid(String accessToken, Map<String, String> massData) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
//        if ("text".equals(messagetype)) {
//            map.put("content", mediaId);
//        } else if ("wxcard".equals(messagetype)) {
//            map.put("wxcard", request);
//        } else {
//            map.put("media_id", mediaId);
//        }
//        map.put("msgtype", types);
        String data = JsonUtil.toJson(massData);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.PREVIEW_MASS_MESSAGE_URL), params, data);
        return JsonUtil.fromJson(result, MassMsgResult.class);
    }

    /**
     * 删除群发
     *
     * @param accessToken
     * @param msgId       发送出去的消息ID
     * @param articleIdx  要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @return
     */
    @Override
    public ResultState deleteMassMessage(String accessToken, String msgId, int articleIdx) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        JsonObject json = new JsonObject();
        json.addProperty("msg_id", msgId);
        json.addProperty("article_idx", articleIdx);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.DELETE_MASS_MESSAGE_URL), params, json.toString());
        return JsonUtil.fromJson(result, ResultState.class);
    }

    /**
     * 查询群发消息发送状态
     *
     * @param accessToken
     * @param msgId       发送出去的消息ID
     * @return
     */
    @Override
    public MassMsgStatusResult getMassMessageStatus(String accessToken, String msgId) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        JsonObject json = new JsonObject();
        json.addProperty("msg_id", msgId);
        String result = HttpUtil.doPost(WechatProperties.getProperty(WechatPropertiesConstant.GET_MASS_MESSAGE_STATUS_URL), params, json.toString());
        return JsonUtil.fromJson(result, MassMsgStatusResult.class);
    }
}
