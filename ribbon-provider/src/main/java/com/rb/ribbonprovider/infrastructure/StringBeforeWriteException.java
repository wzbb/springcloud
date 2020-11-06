package com.rb.ribbonprovider.infrastructure;

/**
 * Created by admin on 2020-11-6.
 */
public class StringBeforeWriteException extends RuntimeException {
    private String result;

    public StringBeforeWriteException(String body) {
        this.result = body;
    }

    public String getResult() {
        return result;
    }
}
