package com.rb.ribbonprovider.infrastructure.handler;

import com.alibaba.fastjson.annotation.JSONField;
import com.rb.ribbonprovider.infrastructure.ErrorInfo;
import com.rb.util.RequestUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by admin on 2020-11-6.
 */
@Data
public class ResultBean<T> implements Serializable {

    @JSONField(name = "result", ordinal = 1)
    private T result;
    @JSONField(name = "success", ordinal = 2)
    private boolean success = true;
    @JSONField(name = "error", ordinal = 3)
    private ErrorInfo errorInfo;
    @JSONField(name = "traceId", ordinal = 4)
    private String traceId;

    public ResultBean() {
        // 404
        if(StringUtils.isEmpty(this.traceId)) {
            this.traceId = UUID.randomUUID().toString();
        }
    }

    public static<T> ResultBean<T> success(T data) {
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setResult(data);
        return resultBean;
    }

    public static<T> ResultBean<T> failure(ErrorInfo errorInfo) {
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setSuccess(false);
        resultBean.setErrorInfo(errorInfo);
        //RequestUtils.getResponse().setStatus((errorInfo.getCode()!=null ? errorInfo.getCode() : HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return resultBean;
    }
}
