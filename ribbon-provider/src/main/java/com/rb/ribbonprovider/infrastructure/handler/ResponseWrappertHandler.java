package com.rb.ribbonprovider.infrastructure.handler;

import com.rb.ribbonprovider.infrastructure.StringBeforeWriteException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by admin on 2020-11-6.
 */
@RestControllerAdvice
public class ResponseWrappertHandler implements ResponseBodyAdvice<Object> {
    public static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";
    public static final String RESPONSE_RESULT_DEFAULT = "RESPONSE_RESULT_DEFAULT";


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if(MediaType.IMAGE_JPEG.getType().equalsIgnoreCase(mediaType.getType())){
            return body;
        }

        if(body instanceof ResultBean){
            return body;
        }
        //处理返回值是String的情况
        if(body instanceof String){
            throw new StringBeforeWriteException(body.toString());
        }

        return ResultBean.success(body);
    }
}
