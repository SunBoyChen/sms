package com.zhihua.sms.zhihua_sms_service;

import com.alibaba.fastjson.JSON;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  消息监听类
 */
@Component
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @JmsListener(destination = "sms")
    public void sendSms(Map map){

        String phoneNumber = (String) map.get("phoneNumber");

        Integer tmplId = (Integer) map.get("tmplId");

        String paramsStr = (String) map.get("params");

        List<String> paramsLis = JSON.parseArray(paramsStr, String.class);

        ArrayList<String> params = new ArrayList<>();

        params.addAll(paramsLis);

        SmsSingleSenderResult result = smsUtil.sendSms(phoneNumber, tmplId, params);

        System.out.println(result);
    }

}
