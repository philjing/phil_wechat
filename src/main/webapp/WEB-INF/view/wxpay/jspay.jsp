<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>微信支付</title>
</head>
<body>
	<h3 class="demos-title" style="margin-bottom: 50px; margin-top: 50px">测试</h3>
	<div class="weui_cell"></div>
	<div class="weui_btn_area" style="margin-top: 80px">
	   <input class="weui_btn weui_btn_primary" type="button" value="点击支付" onclick="pay()"/>
	</div>
	<script type="text/javascript">
	var prepay_id ;
    var paySign ;
    var appId ;
    var timeStamp ;
    var nonceStr ;
    var packageStr ;
    var signType ;
    function pay(){
        var url = '${ctx}/wxpay/jspay';
        $.ajax({
        type:"post",
        url:url,
        dataType:"json",
        data:{openId:'${openId}'},
        success:function(data) {
        	if(data.resultCode == 'SUCCESS'){
        		appId = data.appId;
        		paySign = data.paySign;
        		timeStamp = data.timeStamp;
        		nonceStr = data.nonceStr;
        		packageStr = data.packageStr;
        		signType = data.signType;
                callpay();
            }else{
            	alert("统一下单失败");
            }
        }
    }); 
    }
    
    function onBridgeReady(){
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                 "appId":appId,     //公众号名称，由商户传入
                 "paySign":paySign,         //微信签名
                 "timeStamp":timeStamp, //时间戳，自1970年以来的秒数
                 "nonceStr":nonceStr , //随机串
                 "package":packageStr,  //预支付交易会话标识
                 "signType":signType     //微信签名方式
             },
             function(res){
            	 if(res.err_msg == "get_brand_wcpay_request:ok" ) {
             //window.location.replace("index.html");
             alert('支付成功');
         }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
             alert('支付取消');
         }else if(res.err_msg == "get_brand_wcpay_request:fail" ){
            alert('支付失败');
         } //使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
             }
        );
    }
    function callpay(){
        if (typeof WeixinJSBridge == "undefined"){
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        }else{
            onBridgeReady();
        }
    }
</script>
</body>
</html>
