package com.ci.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ci.config.AlipayConfig;
import com.ci.pojo.entity.Order;
import com.ci.service.OrderService;
import com.ci.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

// xjlugv6874@sandbox.com
// 9428521.24 - 30 = 9428491.24 + 30 = 9428521.24
@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private AlipayConfig aliPayConfig;

    @Autowired
    private OrderService orderService;

    @PostMapping("/pay")
    public String pay(@RequestParam("orderNo") String orderNo,
                      @RequestParam("amount") BigDecimal amount) {
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getGatewayUrl(),
                aliPayConfig.getAppId(),
                aliPayConfig.getPrivateKey(),
                aliPayConfig.getFormat(),
                aliPayConfig.getCharset(),
                aliPayConfig.getPublicKey(),
                aliPayConfig.getSignType());

        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        alipayRequest.setReturnUrl(aliPayConfig.getReturnUrl() + orderNo);
        alipayRequest.setNotifyUrl(aliPayConfig.getNotifyUrl());

        String subject = "订单号：" + orderNo;
        String outTradeNo = orderNo;
        String totalAmount = amount.toString();
        String body = "支付宝支付";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String outTradeNo = params.get("out_trade_no");
            String getPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);

            System.out.println("交易名称: " + params.get("subject"));
            System.out.println("交易状态: " + params.get("trade_status"));
            System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
            System.out.println("商户订单号: " + params.get("out_trade_no"));
            System.out.println("交易金额: " + params.get("total_amount"));
            System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
            System.out.println("买家付款时间: " + params.get("gmt_payment"));
            System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

            Order order = orderService.getById(outTradeNo);

            if (order != null) {
                order.setAlipayNo(alipayTradeNo);
                String nowTime = TimeUtils.getNowTime();
                order.setPaymentTime(nowTime);
                order.setStatus(20);
                orderService.update(order);
            }


            // boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            // if (checkSignature) {
            //     // 验签通过
            //     System.out.println("交易名称: " + params.get("subject"));
            //     System.out.println("交易状态: " + params.get("trade_status"));
            //     System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
            //     System.out.println("商户订单号: " + params.get("out_trade_no"));
            //     System.out.println("交易金额: " + params.get("total_amount"));
            //     System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
            //     System.out.println("买家付款时间: " + params.get("gmt_payment"));
            //     System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));


            // }
        }
        return "success";
    }
}