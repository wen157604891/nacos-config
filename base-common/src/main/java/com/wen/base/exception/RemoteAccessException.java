package com.wen.base.exception;

import com.wen.base.enums.ResultCode;

/**
 * @Author wsw
 * @Date 2023/2/3 15:03
 **/
public class RemoteAccessException extends BusinessException {
    public RemoteAccessException() {
    }

    public RemoteAccessException(Object data) {
        super.data = data;
    }

    public RemoteAccessException(ResultCode resultCode) {
        super(resultCode);
    }

    public RemoteAccessException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public RemoteAccessException(String msg) {
        super(msg);
    }
}
