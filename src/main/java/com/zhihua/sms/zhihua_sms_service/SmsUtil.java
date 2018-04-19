package com.zhihua.sms.zhihua_sms_service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SmsUtil {

    @Autowired
    private Environment env;

    /**
     * 发送短信
     * @param phoneNumber 手机号
     * @param tmplId  模板号
     * @param params  参数
     * @return
     */
    public SmsSingleSenderResult sendSms(String phoneNumber,int tmplId,ArrayList<String> params){

        try {
            //请根据实际 appid 和 appkey 进行开发，以下只作为演示 sdk 使用
            //appid,appkey,templId申请方式可参考接入指南 https://www.qcloud.com/document/product/382/3785#5-.E7.9F.AD.E4.BF.A1.E5.86.85.E5.AE.B9.E9.85.8D.E7.BD.AE
            int appid = Integer.parseInt(env.getProperty("appid"));
            String appkey = env.getProperty("appkey");

            //初始化单发
            SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult singleSenderResult;

            //指定模板单发
            //假设短信模板 id 为 1，模板内容为：测试短信，{1}，{2}，{3}，上学。
            /*ArrayList<String> params = new ArrayList<String>();
            params.add("3214");
            params.add("2");*/
            singleSenderResult = singleSender.sendWithParam("86", phoneNumber, tmplId, params, "", "", "");
            System.out.println(singleSenderResult);
            return singleSenderResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
