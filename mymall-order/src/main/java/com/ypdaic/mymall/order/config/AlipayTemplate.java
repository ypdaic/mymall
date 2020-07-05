package com.ypdaic.mymall.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ypdaic.mymall.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2016102900776837";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCXer3mSmeEcPMGNj0tfeQ//NX3YsW2sY5aks+OINqIPCjFyDCEXJh/vNKeDhaUFGcMmes+4QO4IoDlQXilUx1YoV7rv/hYXJdIBuj57TiyVcmQGgtjTqO/JvMiosydvT0Z+gvR+3Yj6WCy/A/Uknl5Wsy0Ofual+EVf2UtPMnrj9/MQeuKTLpdx/2QN6pT7gIIfAKT6UHNjItsdCfY9lHpyTo6GGX0VEw0+EFx1gaBbedbM3DyD6M9n/QIjzn4eWTbla1WgkWpfPsccQy88RYrvaM9jvbwjrc3yvjXGXHinx+T5ok9z3ny1zDxviFitoGc2G9KWvqVryVQMOgxXa2lAgMBAAECggEAZWISLUrJI9xQ22x737GtjaIUNuHVwGPwCfDOl6qneJ0DEPTefAhZTM09DeoR9IP/QHDYARqq2ZfGOypprAUH1CUVGaY/eixmWB+/2F6MHtfJ8vdrfcjJUYupGRpq5nwcpAE13hF2TazKOqD3m2d5g95guZNBMYPLcw7z0aYo4s41vELlIItRV+sMcsRUdX5G9NC3eHI399utLNQodyrwKDsZDnUAXnxWDoYLRDX23hI1J2cUrsYMkFMUoqVz+5ZfsWGFVEiDRR0KO9mBXrXBSR6o6Uwms4yHetzx2DItHRU/Xps7+Zq5JGVm5wsOR+j687dZDVF7gdXpV4Kzejj9lQKBgQD8tybHFfKbrQKb8yFmAT1b8OEFpJMSjluH/aOpgdXN4fOAZPX8DujJzag5VDrPs3sk0S046os2aqFy9Jep5ANZU/ywCzSZMg0kReTnBnOnX/b+cjIdQvmPS0R3Ej6mD9HRPl6pAyI2pmqKXLDvz/V+lB1vGAp4ne5HOeU/VWewCwKBgQCZcsCiwybiGJpyauVH8stgbc9ujN4U5q3sQPZLOr+Q8Ivb/U/B3tsUbhIOgsQ4R+eAEjnVx/GFp9wX4pjIV5a6G66jXmagcKQ8aKaCQ7YlCXF0xGxDnY99Gm0FcS9AlASpQm/AN74tmShaGZzmeiJ60jqVttZjioAPQwHyT2g3DwKBgF10rdZ5+1ouxQQ/CV7bnoFdtxmLHwiBtL+5wMhyx0p4AMC+lWk9akEW2Ns6lvUwfp3C7WJa+liJXmNQqLWfKM3kDVJdEnqE0+9SGbCF5kdDaef2+ylvFzMytoMCMEDhIMqssfz2t7RWHphEZvqIgVTqGWh4D4dDgFa+xlSBlPK5AoGAIMLHLfjMTGlEUDlSSlIj6aqIJuocYAoAB/i2nMkmLSOuOIGUjaeuzmXRpRd2HS381vMNNf9CKW71JoRSajOJEqLmXirBbm+eIOeW5SjfsniZqHxnQOhawjLqI8dOd1Miy6BLi5ZbHBEe9F2TigF5aw+HQn+OtiHTxW5g9ALQFTECgYAJIshXAQdihZ/S3VoNALwWvhGCkjxdXsYHypMTESUoz3r096mRI17m9+9fNtmRUuzVoViwQ770+/xEs1tD6zhB5DbtsLRL2d0RAzAaQMZ+E2FkjpDLXmvhbZh49lRqywFdSOpAi/GMhLfXvo1Su+rnDxOUxly5DPqav9WNIK2QTg==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAty1eZLK+OdbhRAjX+SAHkjGd9hmZwS/NvSE5mlVJs9/HkJWGFbniY7jH9QjoXhuymjcP/04EAfHyZmYluV+0u1zenfTui/PAZSPDi+aXI+7W4xib3wfxQ/gUniT9xMaqkwbq7uwfADo26lzBGAiiYGHCI+tqHrC4RvFfyqJr4QPKSOqLMAbhF9jMevCkkdelJs6dQ7g6Un9GjGEA6yhEMU8r3DiIV5vTlkzXY3lDHwDtpxGvHuKI33t/wCe4CkvNUxxce5IJKvALr5LA0vDIebvHrU9JPfYQXkLTvgWQ/dN37iVPWxy6fNdY9QWlzOts1q6lXGGimSmspw9vQsqRqwIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url = "http://xb6cg22x28.52http.net/order/pay/alipay/success";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url = "http://localhost:2000/pay/success.html";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
