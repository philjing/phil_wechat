package com.phil.wechat.pay.controller;

import com.phil.modules.config.WechatProperties;
import com.phil.modules.config.WechatPropertiesConstant;
import com.phil.modules.util.*;
import com.phil.wechat.pay.constant.PayConstant;
import com.phil.wechat.pay.model.request.PayShortUrlParams;
import com.phil.wechat.pay.model.request.UnifiedOrderParams;
import com.phil.wechat.pay.model.response.JsPayResult;
import com.phil.wechat.pay.model.response.PayShortUrlResult;
import com.phil.wechat.pay.model.response.UnifiedOrderResult;
import com.phil.wechat.pay.util.PayHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 微信支付三种方式
 *
 * @author phil
 * @date 2017年6月27日
 */
@Controller
@RequestMapping("wechat/pay")
@Slf4j
public class WechatPayController {


    @GetMapping("/payhtml")
    public String oauthBind(Map<String, Object> data) throws Exception {
        UnifiedOrderParams params = new UnifiedOrderParams();
        data.put("params", params);
        return "wechat/pay/h5pay";
    }

    /**
     * 微信内H5调起支付
     *
     * @param params
     * @return
     * @throws Exception
     */
    @PostMapping("h5")
    public Map<String, Object> js(HttpServletRequest request, @ModelAttribute(value = "params") UnifiedOrderParams params) {
        Map<String, Object> data = new HashMap<>();
        if (StringUtils.isEmpty(params) || StringUtils.isEmpty(params.getOpenid())) {
            data.put("code", -1);
            data.put("msg", "支付数据错误");
            return data;
        }
        log.debug("****正在支付的openId****{}", params.getOpenid());
        // 统一下单
        String out_trade_no = PayHelper.createOutTradeNo();
        // int total_fee = 1; // 产品价格1分钱,用于测试
        String spbill_create_ip = HttpUtil.getRemortIP(request);
        log.debug("支付IP {} ", spbill_create_ip);
        String nonce_str = PayHelper.createNonceStr(); // 随机数据
        // 参数组装
        UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
        unifiedOrderParams.setAppid(WechatProperties.getProperty(WechatPropertiesConstant.APP_ID));// 必须
        unifiedOrderParams.setMch_id(PayConstant.MCH_ID);// 必须
        unifiedOrderParams.setOut_trade_no(out_trade_no);// 必须
        unifiedOrderParams.setBody(params.getBody());// 必须 微信支付-支付测试
        unifiedOrderParams.setTotal_fee(params.getTotal_fee()); // 必须
        unifiedOrderParams.setNonce_str(nonce_str); // 必须
        unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
        unifiedOrderParams.setTrade_type(PayConstant.TRADE_TYPE_JSAPI); // 必须
        unifiedOrderParams.setOpenid(params.getOpenid());
        unifiedOrderParams.setNotify_url(WechatProperties.getProperty(WechatPropertiesConstant.PAY_NOTIFY_URL));// 异步通知url
        // 统一下单 请求的Xml(正常的xml格式)
        String unifiedXmL = PayHelper.toPayXml(unifiedOrderParams);// 签名并入util
        // 进行签名校验
        try {
            // 返回<![CDATA[SUCCESS]]>格式的XML
            String unifiedOrderResultXmL = HttpUtil.doPost(
                    WechatProperties.getProperty(WechatPropertiesConstant.UNIFIED_ORDER_URL), null, unifiedXmL);
            if (SignatureUtil.checkValidPaySign(unifiedOrderResultXmL, null)) {
                String timeStamp = PayHelper.createTimeStamp();
                // 统一下单响应
                UnifiedOrderResult unifiedOrderResult = XmlUtil.fromXml(unifiedOrderResultXmL,
                        UnifiedOrderResult.class);
                JsPayResult result = new JsPayResult();
                result.setAppId(WechatProperties.getProperty(WechatPropertiesConstant.APP_ID));
                result.setTimeStamp(timeStamp);
                result.setNonceStr(unifiedOrderResult.getNonce_str());// 直接用返回的
                /**** prepay_id 2小时内都有效，再次支付方法自己重写 ****/
                result.setPackageStr("prepay_id=" + unifiedOrderResult.getPrepay_id());
                /**** 用对象进行签名 ****/
                String paySign = SignatureUtil.md5(result, PayConstant.API_KEY,
                        null);
                result.setPaySign(paySign);
                result.setResultCode(unifiedOrderResult.getResult_code());
                data.put("code", 0);
                data.put("msg", "支付成功");
                data.put("data", result);
            } else {
                data.put("code", -1);
                data.put("msg", "支付签名验证错误");
                log.debug("签名验证错误");
            }
        } catch (Exception e) {
            data.put("code", -1);
            data.put("msg", "支付失败");
            log.debug(e.getMessage());
        }
        return data;
    }

    /**
     * 扫码支付模式一生成二维码
     *
     * @param productId 商品ID
     * @throws IOException
     */
    @GetMapping("M1")
    public Map<String, Object> payone(HttpServletRequest request, String productId) {
        Map<String, Object> data = new HashMap<>();
        String nonce_str = PayHelper.createNonceStr();
        // String product_id = "product_001"; // 推荐根据商品ID生成
        Map<String, Object> packageParams = new TreeMap<>();
        packageParams.put("appid", WechatProperties.getProperty(WechatProperties.getProperty(WechatPropertiesConstant.APP_ID)));
        packageParams.put("mch_id", PayConstant.MCH_ID);
        packageParams.put("product_id", productId);
        packageParams.put("time_stamp", PayHelper.createTimeStamp());
        packageParams.put("nonce_str", nonce_str);
        String str_url = PayHelper.createPayImageUrl(packageParams);
        String sign = SignatureUtil.md5(packageParams, PayConstant.API_KEY, null);
        packageParams.put("sign", sign);
        String payurl = "weixin://wxpay/bizpayurl?sign=" + sign + str_url;
        log.debug("payurl is {}", payurl);
        /**** 转成短链接 ****/
        PayShortUrlParams payShortUrlParams = new PayShortUrlParams();
        payShortUrlParams.setAppid(WechatProperties.getProperty(WechatProperties.getProperty(WechatPropertiesConstant.APP_ID)));
        payShortUrlParams.setMch_id(PayConstant.MCH_ID);
        payShortUrlParams.setLong_url(payurl);
        payShortUrlParams.setNonce_str(nonce_str);
        String urlSign = SignatureUtil.md5(payShortUrlParams, PayConstant.API_KEY,
                null);
        payShortUrlParams.setSign(urlSign);
        String longXml = XmlUtil.toXml(payShortUrlParams);
        String shortResult = HttpUtil.doPost(WechatProperties.getProperty(WechatProperties.getProperty(WechatPropertiesConstant.PAY_SHORT_URL)), null,
                longXml);
        PayShortUrlResult payShortUrlResult = XmlUtil.fromXml(shortResult, PayShortUrlResult.class);
        if (Objects.equals("SUCCESS", payShortUrlResult.getReturn_code())) {
            payurl = payShortUrlResult.getShort_url();
        } else {
            log.debug("错误信息" + payShortUrlResult.getReturn_msg());
        }
        /**** 生成 二维码图片自行实现 ****/
        //定义savePath
        ZXingCodeUtil.toQRCode(payurl, null, "savePath", null);
        return data;
    }

    /**
     * 扫码模式二
     *
     * @param params
     * @throws Exception
     */
    @RequestMapping("M2")
    public Map<String, Object> paytwo(HttpServletRequest request, UnifiedOrderParams params) throws Exception {
        Map<String, Object> data = new HashMap<>();
        // 商户后台系统根据用户选购的商品生成订单。
        String out_trade_no = PayHelper.createNonceStr();
        String product_id = DateUtils.getCurrentTime();// 根据自己的逻辑生成
        // int total_fee = 1; // 1分作测试
        // String attach = "支付测试";
        // String body = "微信支付-支付测试";
        String nonce_str = PayHelper.createNonceStr();
        String spbill_create_ip = HttpUtil.getRemortIP(request); // 获取IP
        UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
        unifiedOrderParams.setAppid(WechatProperties.getProperty(WechatProperties.getProperty(WechatPropertiesConstant.APP_ID)));// 必须
        unifiedOrderParams.setMch_id(PayConstant.MCH_ID);// 必须
        unifiedOrderParams.setOut_trade_no(out_trade_no);
        unifiedOrderParams.setBody(params.getBody());
        unifiedOrderParams.setAttach(params.getAttach());
        unifiedOrderParams.setProduct_id(product_id);// 必须
        unifiedOrderParams.setTotal_fee(params.getTotal_fee());// 必须
        unifiedOrderParams.setNonce_str(nonce_str); // 必须
        unifiedOrderParams.setSpbill_create_ip(spbill_create_ip); // 必须
        unifiedOrderParams.setTrade_type("NATIVE");// 必须
        unifiedOrderParams.setNotify_url(WechatProperties.getProperty(WechatProperties.getProperty(WechatPropertiesConstant.PAY_NOTIFY_URL))); // 异步通知URL
        // 统一下单 请求的Xml(除detail外不需要<![CDATA[product_001]]>格式)
        String unifiedXmL = PayHelper.toPayXml(unifiedOrderParams); // 签名并入util
        // log.debug("统一下单 请求的Xml"+unifiedXmL);
        // 统一下单 返回的xml(<![CDATA[product_001]]>格式)
        String unifiedResultXmL = HttpUtil.doPost(
                WechatProperties.getProperty(WechatProperties.getProperty(WechatPropertiesConstant.UNIFIED_ORDER_URL)), null, unifiedXmL);
        // log.debug("统一下单 返回的xml("+unifiedResultXmL);
        // 统一下单返回 验证签名
        if (SignatureUtil.checkValidPaySign(unifiedResultXmL, null)) {
            UnifiedOrderResult unifiedOrderResult = XmlUtil.fromXml(unifiedResultXmL,
                    UnifiedOrderResult.class);
            if (Objects.equals("SUCCESS", unifiedOrderResult.getReturn_code())
                    && Objects.equals("SUCCESS", unifiedOrderResult.getResult_code())) {
                /**** 根据unifiedOrderResult.getCode_url()生成有效期为2小时的二维码 ****/


                /**** 根据product_id再次支付方法自己写 ****/
            }
        } else {
            log.debug("签名验证错误");
        }
        return data;
    }
}