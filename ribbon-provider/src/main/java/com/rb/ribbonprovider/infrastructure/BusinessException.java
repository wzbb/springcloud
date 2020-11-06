package com.rb.ribbonprovider.infrastructure;

/**
 * Created by admin on 2020-11-6.
 */
public class BusinessException extends RuntimeException {
    private final String msg;
    private final int code;


    public BusinessException(ResultCode businessError, Throwable cause) {
        super(businessError.toString(), cause);
        this.msg = businessError.messge();
        this.code = businessError.code();
    }

    public String getMessage() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
