/**
 * FileName: WechatQRCodeController
 * Author:   Phil
 * Date:     11/21/2018 5:11 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.qrcode.controller;

import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.qrcode.model.request.QRcodeParams;
import com.phil.wechat.qrcode.service.WechatQRCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 5:11 PM
 * @since 1.0
 */
@RestController
@RequestMapping("api/qrcode")
@Slf4j
public class WechatQRCodeController {

    @Autowired
    private WechatQRCodeService wechatQRCodeService;

    @Autowired
    private WechatAuthService wechatAuthService;

    /**
     * 参数形式
     *
     **/
    @PostMapping("create/qrcode/V1")
    public Map<String, Object> createTempTicket(@RequestBody QRcodeParams params) {
        String accessToken = wechatAuthService.getAccessToken();
        Map<String, Object> data = new HashMap<>();
        if (Objects.isNull(params.getSceneStr()) && Objects.isNull(params.getSceneId())) {
            data.put("code", -1);
            data.put("msg", "参数为空");
            return data;
        }
        String ticket;
        //expireSeconds<0表示永久，否则是临时
        int expireSeconds = params.getExpireSeconds();
        if (expireSeconds == 0) {
            expireSeconds = 30;
        }
        if (params.getExpireSeconds() < 0) {
            if (!Objects.isNull(params.getSceneStr())) {
                ticket = wechatQRCodeService.createForeverTicket(accessToken, params.getSceneStr());
                data.put("msg", "生成永久字符串二维码成功");
            } else {
                ticket = wechatQRCodeService.createForeverTicket(accessToken, params.getSceneId());
                data.put("msg", "生成永久整型二维码成功");
            }
        } else {
            if (!Objects.isNull(params.getSceneStr())) {
                ticket = wechatQRCodeService.createTempTicket(accessToken, params.getSceneStr(), expireSeconds);
                data.put("msg", "生成临时字符串二维码成功");
            } else {
                ticket = wechatQRCodeService.createTempTicket(accessToken, params.getSceneId(), expireSeconds);
                data.put("msg", "生成临时整型二维码成功");
            }
        }
        String url = wechatQRCodeService.showQrCode(accessToken, ticket, true);
        data.put("code", 0);
        data.put("data", url);
        if (expireSeconds > -1){
            data.put("expireSeconds", expireSeconds);
        }
        return data;
    }

    /**
     * 文件流形式(示例)
     * 描述:通过文印传来的令牌,项目设定,打印设备信息作为参数,在公众号生成带参二维码
     */
    @GetMapping(value = "createDevQRCode/{TokenId}/{ProjectId}/{DeviceId}")
    public void createDevQRCode(@PathVariable("TokenId") String tokenId,
                                @PathVariable("ProjectId") Integer projectId, @PathVariable("DeviceId") String deviceId, HttpServletResponse response) throws Exception {
        if (StringUtils.isBlank(tokenId) || projectId == null || StringUtils.isBlank(deviceId)) {
            throw new Exception("自定义报错");
        }
        String accessToken = wechatAuthService.getAccessToken();
        String ticket = wechatQRCodeService.createForeverTicket(accessToken, tokenId + DigestUtils.md5Hex(projectId.toString()) + deviceId);
        String url = wechatQRCodeService.showQrCode(accessToken, ticket, true);

        try {
            BufferedImage img = new BufferedImage(550, 978, BufferedImage.TYPE_INT_RGB);
            //读取海报图
            BufferedImage bg = ImageIO.read(new URL("http://pi43vjmbu.bkt.clouddn.com/%E4%BA%8C%E7%BB%B4%E7%A0%81%E5%88%86%E4%BA%AB%E6%B5%B7%E6%8A%A5.png"));
            //读取微信生成的带参数二维码
            BufferedImage qRCodeImg = ImageIO.read(new URL(url));

            //开启画图
            Graphics g = img.getGraphics();
            // 绘制背景缩小后的图
            g.drawImage(bg.getScaledInstance(550, 978, Image.SCALE_DEFAULT), 0, 0, null);
            // 绘制二维码缩小后的图
            g.drawImage(qRCodeImg.getScaledInstance(210, 210, Image.SCALE_DEFAULT), 38, 698, null);

            g.setColor(Color.black);
            g.dispose();
            ImageIO.write(img, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            Map<String, Object> data = new HashMap<>();
            data.put("msg", "生成二维码失败");
            data.put("code", -1);
            IOUtils.write(data.toString(), response.getOutputStream(), StandardCharsets.UTF_8);
        }
//        HttpUtil.toOutput(url, response.getOutputStream());
    }
}
