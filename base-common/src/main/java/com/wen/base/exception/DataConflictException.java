package com.wen.base.exception;

import com.wen.base.enums.ResultCode;

/**
 * @Author wsw
 * @Date 2023/2/2 15:30
 **/
public class DataConflictException extends BusinessException {
    public DataConflictException() {
    }

    public DataConflictException(Object data) {
        super.data = data;
    }

    public DataConflictException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataConflictException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataConflictException(String msg) {
        super(msg);
    }
}
