package com.ci.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/7 16:28
 */

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;
    private String returnUrl;

    private String privateKey;
    private String publicKey;
    private String gatewayUrl;
    private String charset;
    private String format;
    private String signType;

}
