package com.mvc.cryptovault.console.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import com.mvc.cryptovault.common.bean.CommonToken;
import com.mvc.cryptovault.console.common.AbstractService;
import com.mvc.cryptovault.console.common.BaseService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;

/**
 * JPush
 *
 * @author qiyichen
 * @create 2018/11/15 12:15
 */
@Service
@Log4j
public class JPushService extends AbstractService<CommonToken> implements BaseService<CommonToken>, PushService {

    @Autowired
    JPushClient jPushClient;

    public void send(BigInteger userId, String msg, Map<String, String> extra) {
        PushPayload payload = buildPush(userId, msg, extra);
        try {
            PushResult result = jPushClient.sendPush(payload);
            log.info("Got result - " + result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    private PushPayload buildPush(BigInteger userId, String msg, Map<String, String> extra) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(String.valueOf(userId)))
                .setMessage(Message.newBuilder().setMsgContent(msg).addExtras(extra).build())
                .build();
    }

}