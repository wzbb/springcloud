package com.rb.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rb.ribbonprovider.infrastructure.ErrorInfo;
import com.rb.ribbonprovider.log.RestLog;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by admin on 2020-11-9.
 */
public class LogUtils {

    private static Logger debugLog = LoggerFactory.getLogger("debug");
    private static Logger infoLog = LoggerFactory.getLogger("info");
    private static Logger warnLog = LoggerFactory.getLogger("warn");
    private static Logger errorLog = LoggerFactory.getLogger("error");

    public static void debug(Object msg) {
        if (msg != null && debugLog.isDebugEnabled()) {
            debugLog.debug(msg.toString());
        }
    }

    public static void info(Object msg) {
        if (msg != null && infoLog.isInfoEnabled()) {
            infoLog.info(msg.toString());
        }
    }

    public static void warn(Object msg) {
        if (msg != null) {
            warnLog.warn(msg.toString());
        }
    }

    public static void error(Object msg) {
        if (msg != null) {
            errorLog.error(msg.toString());
        }
    }

    public static void recordInfo(RestLog restLog){
        info(restLog.toString());
    }

    public static void recordError(RestLog restLog) {
        error(JSONObject.toJSONString(restLog, SerializerFeature.WriteMapNullValue));
    }

    public static RestLog createLog(HttpServletRequest request, HttpServletResponse response, Method method, JoinPoint joinPoint, LocalDateTime startTime) {
        String httpVerb = request.getMethod();
        int httpCode = response.getStatus();
        String url = request.getRequestURL().toString();
        String accessToken = request.getHeader("token");
        //accessToken = JwtUtils.getJwtPlayLoad(accessToken.substring(7));
        RestLog restLog = new RestLog();
        HashMap argsMap = new HashMap();
        Object[] obj = joinPoint.getArgs();
//        if (obj.length > 0) {
//            //获取请求参数
//            Enumeration<String> enumeration = request.getParameterNames();
//            while (enumeration.hasMoreElements()) {
//                String parameter = enumeration.nextElement();
//                argsMap.put(parameter, request.getParameter(parameter));
//            }
//        }
        if (obj.length > 0) {
            //获取请求参数
            for(int i=0;i<obj.length;i++) {
                if(obj[i] != null) {
                    argsMap.put(method.getParameters()[i], obj[i].toString());
                }
            }
        }
        restLog.setHttpCode(httpCode);
        restLog.setHttpVerb(httpVerb);
        restLog.setOp(url);
        restLog.setArgs(argsMap);
        restLog.setStartTime(startTime);
        restLog.setAccessToken(accessToken);
        return restLog;
    }

    public static RestLog createErrorLog(HttpServletRequest request, HttpServletResponse response, ErrorInfo errorInfo, LocalDateTime startTime) {
        String httpVerb = request.getMethod();
        int httpCode = response.getStatus();
        String url = request.getRequestURL().toString();
        String accessToken = request.getHeader("token");
        //accessToken = JwtUtils.getJwtPlayLoad(accessToken.substring(7));
        RestLog restLog = new RestLog();
        HashMap argsMap = new HashMap();
        restLog.setHttpCode(httpCode);
        restLog.setHttpVerb(httpVerb);
        restLog.setOp(url);
        //获取请求参数
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            argsMap.put(parameter, request.getParameter(parameter));
        }
        restLog.setArgs(argsMap);
        restLog.setStartTime(startTime);
        restLog.setResult(errorInfo);
        restLog.setAccessToken(accessToken);
        return restLog;
    }
}
