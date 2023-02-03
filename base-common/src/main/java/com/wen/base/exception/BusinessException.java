package com.wen.base.exception;

import com.wen.base.enums.BusinessExceptionEnum;
import com.wen.base.enums.ResultCode;

/**
 * @Author wsw
 * @Date 2023/2/2 15:23
 **/
public class BusinessException extends RuntimeException {
    protected String code;
    protected String message;
    protected ResultCode resultCode;
    protected Object data;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BusinessException() {
        BusinessExceptionEnum exceptionEnum = BusinessExceptionEnum.getByEClass(this.getClass());
        if (exceptionEnum != null) {
            this.resultCode = exceptionEnum.getResultCode();
            this.code = exceptionEnum.getResultCode().code().toString();
            this.message = exceptionEnum.getResultCode().message();
        }

    }

    public BusinessException(String message) {
        this();
        this.message = message;
    }

    public BusinessException(ResultCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

    public BusinessException(ResultCode resultCode, String message) {
        this(resultCode);
        this.message = message;
    }

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code().toString();
        this.message = resultCode.message();
    }

    public BusinessException(String code, String message, Object data) {
        this();
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
