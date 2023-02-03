package com.wen.base.exception;

import com.wen.base.enums.ResultCode;

/**
 * @Author wsw
 * @Date 2023/2/3 15:00
 **/
public class MethodNotAllowException extends BusinessException {
    public MethodNotAllowException() {
    }

    public MethodNotAllowException(Object data) {
        super.data = data;
    }

    public MethodNotAllowException(ResultCode resultCode) {
        super(resultCode);
    }

    public MethodNotAllowException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public MethodNotAllowException(String msg) {
        super(msg);
    }
}
