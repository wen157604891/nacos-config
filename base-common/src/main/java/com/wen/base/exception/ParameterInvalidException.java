package com.wen.base.exception;

import com.wen.base.enums.ResultCode;

/**
 * @Author wsw
 * @Date 2023/2/2 15:26
 **/
public class ParameterInvalidException extends BusinessException {
    public ParameterInvalidException() {
    }

    public ParameterInvalidException(Object data) {
        super.data = data;
    }

    public ParameterInvalidException(ResultCode resultCode) {
        super(resultCode);
    }

    public ParameterInvalidException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public ParameterInvalidException(String msg) {
        super(msg);
    }
}
