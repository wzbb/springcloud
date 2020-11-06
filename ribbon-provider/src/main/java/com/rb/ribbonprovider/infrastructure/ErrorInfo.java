package com.rb.ribbonprovider.infrastructure;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ErrorInfo {
    @JSONField(name = "code", ordinal = 1)
    private Integer code;

    @JSONField(name = "message", ordinal = 2)
    private String message;

    @JSONField(name = "details", ordinal = 3)
    private String details;
}
