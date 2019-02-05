package com.phil.wechat.material.service.impl;

import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.base.BaseJunitTest;
import com.phil.wechat.material.constant.WechatMaterialConstant;
import com.phil.wechat.material.model.request.UploadNewsEntity;
import com.phil.wechat.material.service.WechatMaterialService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class WechatMaterialServiceImplTest extends BaseJunitTest {

    @Autowired
    private WechatAuthService wechatAuthService;

    @Autowired
    private WechatMaterialService wechatMaterialService;

    @Test
    public void uploadTempMediaFile() {
        String filePath = "C:/Users/Phil/Pictures/cc0a42661f668d17e18d0a0f3a699909.jpeg";
        String accessToken = wechatAuthService.getAccessToken();
        System.out.println("新增临时本地图片素材,返回" + wechatMaterialService.uploadTempMediaFile(accessToken,
                WechatMaterialConstant.MATERIAL_UPLOAD_TYPE_IMAGE, filePath));
        //新增临时本地图片素材,返回MassNewsResult(type=image, mediaId=rh0D9L_B5Ty_-EcwIFULGjb57eCGAbQZRz8bNrWnfcLMiMz2Pm7vIQCdUKzC8C_8, createdAt=1543648966, url=null)
    }

    @Test
    public void uploadTempMediaUrl() {
        String url = "http://b-ssl.duitang.com/uploads/item/201503/14/20150314212812_kCLmy.jpeg";
        String accessToken = wechatAuthService.getAccessToken();
        System.out.println("新增临时网络图片素材,返回 " + wechatMaterialService.uploadTempMediaUrl(accessToken,
                WechatMaterialConstant.MATERIAL_UPLOAD_TYPE_IMAGE, url));
        //新增临时网络图片素材,返回 MassNewsResult(type=image, mediaId=lgbD-K0ltDTmsW2dSxus6sFxqqFc3mU_NiIAOev_U_mU2Yqa6iSv8KPF5e5WE7yC, createdAt=1543648973, url=null)
    }

    @Test
    public void uploadForeverMediaFile() {
        String filePath = "C:/Users/Phil/Pictures/cc0a42661f668d17e18d0a0f3a699909.jpeg";
        String accessToken = wechatAuthService.getAccessToken();
        System.out.println("新增永久本地图片素材,返回" + wechatMaterialService.uploadForeverMediaFile(accessToken,
                WechatMaterialConstant.MATERIAL_UPLOAD_TYPE_IMAGE, filePath));
        //新增永久本地图片素材,返回MassNewsResult(type=null, mediaId=tbP52CbH1vuCLlyUAt2rlUEZKK230SjWIaB9xablAPw, createdAt=0, url=http://mmbiz.qpic.cn/mmbiz_jpg/L8zibbZGicwDyd6CV9JpbEe6T7rjqK2fBibCrZAxEvwDCJKHAgtfUialcnNlcjxTsAmycHSKjrO6KzdDUMeDwhYsIA/0?wx_fmt=jpeg)
    }

    @Test
    public void uploadForeverMediaUrl() {
        String url = "http://www.59xihuan.cn/uploads/allimg/201309/9181378183464-lp.jpg";
//        String url = "http://b-ssl.duitang.com/uploads/item/201503/14/20150314212812_kCLmy.jpeg";
        String accessToken = wechatAuthService.getAccessToken();
        System.out.println("新增永久网络图片素材, 返回" + wechatMaterialService.uploadForeverMediaUrl(accessToken,
                WechatMaterialConstant.MATERIAL_UPLOAD_TYPE_IMAGE, url));
        //新增永久网络图片素材, 返回MassNewsResult(type=null, mediaId=GG16CzQSj3di2OTScAcNAzmoxg_5_KTyxsJ6uEEBpiM, createdAt=0, url=http://mmbiz.qpic.cn/mmbiz_jpg/3WjFdhSpzNwwEwTpWMU4j8UVddOSDGR2zRuJsQZXaRYnsIB3g4yESBqjxkf1e7ruIWYfUO5FtRWIibaVwt6icOHw/0?wx_fmt=jpeg)
    }

    @Test
    public void uploadNewsMedia() {
        String accessToken = wechatAuthService.getAccessToken();
        UploadNewsEntity entity = new UploadNewsEntity();
        UploadNewsEntity.UploadNewsArticle article1 = new UploadNewsEntity.UploadNewsArticle();
        article1.setThumbMediaId("tbP52CbH1vuCLlyUAt2rlWEIB6qGsfypnI01Jwwonjg");
        article1.setAuthor("article1");
        article1.setTitle("article1");
        article1.setContentSourceUrl("");
        article1.setContent("article1");
        article1.setDigest("article1");
        article1.setShowConverPic(1);
        entity.addArticle(article1);
        UploadNewsEntity.UploadNewsArticle article2 = new UploadNewsEntity.UploadNewsArticle();
        article2.setThumbMediaId("tbP52CbH1vuCLlyUAt2rlRjbl4sO2dPYqvCLZNwIorg");
        article2.setAuthor("article2");
        article2.setTitle("article2");
        article2.setContentSourceUrl("");
        article2.setContent("article2");
        article2.setDigest("article2");
        article2.setShowConverPic(0);
        entity.addArticle(article2);
        System.out.println("新增永久图文素材, 返回" + wechatMaterialService.uploadNewsMedia(accessToken, entity));
        //新增永久图文素材, 返回tbP52CbH1vuCLlyUAt2rlVvIMceFKJmY663R_urJAYc
    }

    @Test
    public void uploadFileForNewsMedia() {
        String accessToken = wechatAuthService.getAccessToken();
        String filePath = "D:\\Project\\Phil\\Wechat\\phil_new_wechat\\src\\main\\webapp\\image\\00_01.jpeg";
        System.out.println(wechatMaterialService.uploadFileForNewsMedia(accessToken, filePath));
        //http://mmbiz.qpic.cn/mmbiz_jpg/L8zibbZGicwDw6sv1WWkFK1nCUDY9bJrHiaEbJuTS37XbJR3hEiblAkibzjdTHdHKqXb6Ij53CDfNwztRsOwljibEp8A/0
    }

    @Test
    public void uploadUrlForNewsMedia() {
        String accessToken = wechatAuthService.getAccessToken();
        String url = "http://www.59xihuan.cn//uploads/allimg/20130728/64511374987081-lp.jpg";
        System.out.println(wechatMaterialService.uploadUrlForNewsMedia(accessToken, url));
        //http://mmbiz.qpic.cn/mmbiz_jpg/L8zibbZGicwDw6sv1WWkFK1nCUDY9bJrHiaQXwtf52SyM4ftxVeuickvh1rDLPMaeE1jDxgpWice9TgJOzYPIRAy8uA/0
    }

    @Test
    public void uploadForeverVideoMediaFile() {
        String filePath = "D:/Project/Phil/Wechat/phil_new_wechat/src/main/webapp/video/1539717180390.mp4";
        String accessToken = wechatAuthService.getAccessToken();
        System.out.println("新增永久本地视频素材, 返回" + wechatMaterialService.uploadForeverMediaFile(accessToken,
                "title", "test", filePath));
        //新增永久本地视频素材, 返回GG16CzQSj3di2OTScAcNA-LTwMdsrKszM7M677NJyic
    }

    @Test
    public void uploadForeverVideoMediaUrl() {
        //String url = "http://vs6.bdstatic.com/browse_static/v3/common/widget/global/player/newPlayer_e2332cd1.swf";
        //转换
        String url = "http://xiaozhaozhao.nat300.top/video/1539717180390.mp4";
        String accessToken = wechatAuthService.getAccessToken();
        System.out.println("新增永久网络视频素材, 返回" + wechatMaterialService.uploadForeverMediaUrl(accessToken,
                "title", "test", url));
        // 新增永久网络视频素材, 返回GG16CzQSj3di2OTScAcNA6OWJlBHKk8sXSmbgPzxEQo
    }

    @Test
    public void getTempMaterial() {
        String accessToken = wechatAuthService.getAccessToken();
        String mediaId = "c985PlW3avLd3w9gUclA75geC9PFFozfX6-Jn6s9kRWwV2gu0KHSaBEDJu0Eugno";
        System.out.println(wechatMaterialService.getTempMaterial(accessToken, mediaId));
    }

    @Test
    public void getMaterial() {
        String accessToken = wechatAuthService.getAccessToken();
//        String mediaId = "tbP52CbH1vuCLlyUAt2rlUEZKK230SjWIaB9xablAPw"; //图片
        String mediaId = "tbP52CbH1vuCLlyUAt2rlVvIMceFKJmY663R_urJAYc"; //图文
        System.out.println(wechatMaterialService.getMaterial(accessToken, mediaId));
    }

    @Test
    public void deleteMaterial() {
        String mediaId = "tbP52CbH1vuCLlyUAt2rlYaBvU3BHrs8g0-XxdnNILY";
        //tbP52CbH1vuCLlyUAt2rlfnoE7EJNJvPvEv0nFkezsw
        //tbP52CbH1vuCLlyUAt2rlYUYsAvjhY7UmdgQ_8qnip4
        String accessToken = wechatAuthService.getAccessToken();
        System.out.println(wechatMaterialService.deleteMaterial(accessToken, mediaId));
        //ResultState(errcode=0, errmsg=ok)
    }

    @Test
    public void geMaterialCount() {
        String accessToken = wechatAuthService.getAccessToken();
        System.out.println(wechatMaterialService.geMaterialCount(accessToken));
        //MaterialCountResult(voiceCount=0, videoCount=5, imageCount=15, newsCount=2)
    }
}