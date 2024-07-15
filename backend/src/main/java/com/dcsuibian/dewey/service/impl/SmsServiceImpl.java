package com.dcsuibian.dewey.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dcsuibian.dewey.exception.BusinessException;
import com.dcsuibian.dewey.service.SmsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    @Value("${alibaba.cloud.access-key-id}")
    private String accessKeyId;
    @Value("${alibaba.cloud.access-key-secret}")
    private String accessKeySecret;
    @Value("${alibaba.cloud.sms.endpoint}")
    private String endpoint;
    @Value("${alibaba.cloud.sms.sign-name}")
    private String signName;
    @Value("${alibaba.cloud.sms.template-code}")
    private String templateCode;
    private final ObjectMapper objectMapper;

    @Autowired
    public SmsServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(String phoneNumber, String message) {
        try {
            Client client = createClient();
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("code", message);
            SendSmsRequest sendSmsRequest = new SendSmsRequest().setSignName(signName).setTemplateCode(templateCode).setPhoneNumbers(phoneNumber).setTemplateParam(objectMapper.writeValueAsString(paramMap));
            RuntimeOptions runtimeOptions = new RuntimeOptions();
            SendSmsResponse response = client.sendSmsWithOptions(sendSmsRequest, runtimeOptions);
            if (!"ok".equalsIgnoreCase(response.body.code)) {
                log.error("发送短信失败: {}", response.body.message);
                throw new BusinessException("发送短信失败: " + response.body.message);
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("发送短信失败", e);
            throw new RuntimeException("发送短信失败");
        }
    }

    private Client createClient() {
        try {
            Config config = new Config().setAccessKeyId(accessKeyId).setAccessKeySecret(accessKeySecret);
            config.endpoint = endpoint;
            return new Client(config);
        } catch (Exception e) {
            log.error("创建短信客户端失败", e);
            throw new RuntimeException("创建短信客户端失败");
        }
    }
}
