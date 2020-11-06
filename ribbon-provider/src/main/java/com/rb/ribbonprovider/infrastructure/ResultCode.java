package com.rb.ribbonprovider.infrastructure;

/**
 * Created by admin on 2020-11-6.
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(0, "成功"),
    /* 失败状态码 */
    FAIL(-1, "失败"),

    /* 参数错误 */
    PARAM_IS_INVALID(2001, "参数无效"),
    PARAM_IS_BLANK(2002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(2003, "参数类型错误"),
    PARAM_NOT_COMPLETE(2004, "参数缺失"),

    /* 请求异常 */
    REQUEST_ERROR(300, "请求异常"),

    /* 用户错误 */
    BUSINESS_ERROR(400, "业务异常"),
    BUSINESS_UNAUTHORIZED(401, "未登陆"),
    BUSINESS_FORBIDDEN(403, "无权限"),
    BUSINESS_USER_NOT_LOGGED_IN(4001, "用户未登录"),
    BUSINESS_USER_LOGIN_ERROR(4002, "账号或密码错误"),
    BUSINESS_USER_ACCOUNT_FORBIDDEN(4003, "账号已禁用"),
    BUSINESS_USER_NOT_EXIST(4004, "用户不存在"),
    BUSINESS_USER_HAS_EXISTED(4005, "用户已存在"),

    /* 服务异常 */
    SERVER_ERROR(500, "服务异常"),
    /* 运行时异常 */
    RUNTIME_ERROR(5001, "运行时异常"),
    NULL_POINTER_ERROR(5002, "空指针异常"),
    CLASS_CAST_ERROR(5003, "类型转换异常"),
    IO_ERROR(5004, "IO异常"),
    NO_SUCH_METHOD_ERROR(5005, "未知方法异常"),
    INDEX_OUT_OF_BOUNDS_ERROR(5006, "数组越界异常");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String messge() {
        return this.message;
    }

    @Override
    public String toString() {
        return super.toString() + " code:" + code + "; msg:" + message;
    }
}
