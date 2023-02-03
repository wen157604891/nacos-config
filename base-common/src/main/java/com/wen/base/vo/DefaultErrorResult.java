package com.wen.base.vo;

import com.wen.base.enums.BusinessExceptionEnum;
import com.wen.base.enums.ResultCode;
import com.wen.base.exception.BusinessException;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @Author wsw
 * @Date 2023/2/2 15:35
 **/
public class DefaultErrorResult implements Result {
    private Integer status;
    private String error;
    private String message;
    private Integer code;
    private String path;
    private String exception;
    private Object errors;
    private Date timestamp;

    public DefaultErrorResult() {
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getException() {
        return this.exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Object getErrors() {
        return this.errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static DefaultErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus, Object errors) {
        DefaultErrorResult result = failure(resultCode, e, httpStatus);
        result.setErrors(errors);
        return result;
    }

    public static DefaultErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus) {
        DefaultErrorResult result = new DefaultErrorResult();
        result.setCode(resultCode.code());
        result.setMessage(resultCode.message());
        result.setStatus(httpStatus.value());
        result.setError(httpStatus.getReasonPhrase());
        result.setException(e.getClass().getName());
        String path = null;
        result.setPath((String)path);
        result.setTimestamp(new Date());
        return result;
    }

    public static DefaultErrorResult failure(BusinessException e) {
        BusinessExceptionEnum ee = BusinessExceptionEnum.getByEClass(e.getClass());
        if (ee != null) {
            return failure(ee.getResultCode(), e, ee.getHttpStatus(), e.getData());
        } else {
            DefaultErrorResult defaultErrorResult = failure(e.getResultCode() == null ? ResultCode.SYSTEM_INNER_ERROR : e.getResultCode(), e, HttpStatus.OK, e.getData());
            defaultErrorResult.setMessage(e.getMessage());
            return defaultErrorResult;
        }
    }
}
