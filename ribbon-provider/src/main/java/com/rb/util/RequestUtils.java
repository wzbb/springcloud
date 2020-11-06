package com.rb.util;

import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class RequestUtils {
    private RequestUtils() {

    }

    /**
     * 是 free 的请求，pass，不需要拦截任何 header，token，也不记录 rest 日志
     */
//    public static boolean isFree(Object handler) {
//
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        // 如果有前端相关注解，不进行拦截处理
//        if (((HandlerMethod) handler).hasMethodAnnotation(Frontend.class)) {
//            return true;
//        }
//
//        return false;
//    }

    /**
     * 是否 http verb 为 Options 的请求
     */
    public static boolean isOptions(HttpServletRequest request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethod());
    }

    /**
     * 获取 Cookie 信息, 只存储键值
     */
    private static HashMap fetchCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        HashMap<String, String> cookieValue = new LinkedHashMap<>();
        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                cookieValue.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieValue;
    }

    /**
     * static，线程安全的，因为来自 RequestContextHolder，是线程变量
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            // 在 filter 上下文中， RequestContextHolder.getRequestAttributes 会是null
            return null;
        } else {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
    }

    /**
     * static，线程安全的，因为来自 RequestContextHolder，是线程变量
     */
    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            // 在 filter 上下文中， RequestContextHolder.getRequestAttributes 会是null
            return null;
        } else {
            return ((ServletRequestAttributes) requestAttributes).getResponse();
        }
    }
}
