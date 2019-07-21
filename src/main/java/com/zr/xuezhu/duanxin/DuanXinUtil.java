package com.zr.xuezhu.duanxin;

import com.alibaba.fastjson.JSON;
import com.zr.xuezhu.duanxin.request.SmsSendRequest;
import com.zr.xuezhu.duanxin.response.SmsSendResponse;
import com.zr.xuezhu.duanxin.util.ChuangLanSmsUtil;
import com.zr.xuezhu.util.ResultVo;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author tianyh
 * @Description:普通短信发送
 */
public class DuanXinUtil {
    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "N3107751";
    // 用户平台API密码(非登录密码)
    public static String password = "oMqkVbmLSic919";
    public static ResultVo duanxin(String msg, String iphone){
        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
        // 短信内容
//            String msg = "【创蓝253】你好,你的验证码是123456";
        //手机号码
        String phone = iphone;
        //状态报告
        String report = "true";

        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, phone, report);
        if (!smsSingleRequest.getReport().equals("true")){
            return ResultVo.error("短信发送失败，请重新发送！");
        }

        String requestJson = JSON.toJSONString(smsSingleRequest);

        System.out.println("before request string is: "+requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

        System.out.println("response after request result is :"+response);

        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        System.out.println("response  toString is :"+smsSingleResponse);
        return ResultVo.success();
    }
}
