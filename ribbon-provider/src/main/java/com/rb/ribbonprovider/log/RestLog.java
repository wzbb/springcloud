package com.rb.ribbonprovider.log;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * client 端调用日志
 */
@Data
public class RestLog<T> {
    // region 身份相关
    @JSONField(name = "clientIp", ordinal = 1)
    private String clientIp;

    @JSONField(name = "sessionId", ordinal = 4)
    private String sessionId;

    @JSONField(name = "accessToken", ordinal = 5)
    private String accessToken;

    @JSONField(name = "traceId", ordinal = 6)
    private String traceId;

    // region 业务相关
    @JSONField(name = "httpCode", ordinal = 7)
    private int httpCode;

    @JSONField(name = "httpVerb", ordinal = 8)
    private String httpVerb;

    @JSONField(name = "op", ordinal = 9)
    private String op;

    @JSONField(name = "args", ordinal = 10)
    private HashMap args;

    @JSONField(name = "result", ordinal = 11)
    private T result;

    @JSONField(name = "startTime", ordinal = 12)
    private LocalDateTime startTime;

    @JSONField(name = "endTime", ordinal = 13)
    private LocalDateTime endTime;

    @JSONField(name = "takenTime", ordinal = 14)
    private long takenTime;
    // endregion
    @JSONField(name = "method",ordinal = 15)
    private String method;

//    public RestLog() {
//        this.clientIp = IpAddressUtils.getIpAdrress();
//        this.sessionId = ThreadLocalPools.getSessionIdLocal().get();
//        this.accessToken = ThreadLocalPools.getAccessTokenLocalValue();
//        this.traceId = ThreadLocalPools.getTraceIDLocal().get();
//    }
}
