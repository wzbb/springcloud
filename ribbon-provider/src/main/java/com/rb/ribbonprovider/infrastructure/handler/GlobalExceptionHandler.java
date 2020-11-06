package com.rb.ribbonprovider.infrastructure.handler;

import com.rb.ribbonprovider.infrastructure.BusinessException;
import com.rb.ribbonprovider.infrastructure.ErrorInfo;
import com.rb.ribbonprovider.infrastructure.ResultCode;
import com.rb.ribbonprovider.infrastructure.StringBeforeWriteException;
import com.rb.util.RequestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2020-11-6.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultBean<Object> businessExceptionHandler(BusinessException e){
        return getErrorBean(e, e.getCode());
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public ResultBean<Object> runtimeExceptionHandler(RuntimeException ex) {
//        if(ex instanceof StringBeforeWriteException){
//            return ResultBean.success(((StringBeforeWriteException) ex).getResult());
//        }
//        return getErrorBean(ex, ResultCode.SERVER_ERROR.code());
//    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultBean<Object> runtimeExceptionHandler(RuntimeException e) {
        return getErrorBean(e, ResultCode.SERVER_ERROR.code());
    }

    private ErrorInfo getError(Exception e, Integer httpCode) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode(httpCode);
        errorInfo.setMessage(e.getMessage());
        errorInfo.setDetails(e.getClass().getName());
        return errorInfo;
    }

    private ResultBean<Object> getErrorBean(Exception e, Integer httpCode) {
        //设置httpCode
        //RequestUtils.getResponse().setStatus(httpCode);
        //记录日志
        ErrorInfo errorInfo = getError(e, httpCode);
        return ResultBean.failure(errorInfo);
    }
}
