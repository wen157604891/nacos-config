package com.wen.base.exception;

/**
 * @Author wsw
 * @Date 2023/2/2 15:39
 **/
public class FeignInvokeException extends RuntimeException {
    protected Integer code;
    protected String message;
    protected Object data;

    public FeignInvokeException(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
