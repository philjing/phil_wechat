package com.phil.wechat.pay.model.response;

import com.phil.wechat.pay.model.request.JsPayParams;
import lombok.Getter;
import lombok.Setter;

/**
 * 微信内H5返回结果
 *
 * @author phil
 * @date 2017年6月27日
 */
@Getter
@Setter
public class JsPayResult extends JsPayParams {

    private static final long serialVersionUID = 392188712101246402L;
    private String errMsg;
    private String resultCode;

}
