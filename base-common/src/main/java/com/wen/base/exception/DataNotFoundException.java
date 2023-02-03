package com.wen.base.exception;

import com.wen.base.enums.ResultCode;

/**
 * @Author wsw
 * @Date 2023/2/2 15:31
 **/
public class DataNotFoundException extends BusinessException {
    private static final long serialVersionUID = 3721036867889297081L;

    public DataNotFoundException() {
    }

    public DataNotFoundException(Object data) {
        super.data = data;
    }

    public DataNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataNotFoundException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }
}
