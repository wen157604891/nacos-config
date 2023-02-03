package com.wen.base.exception;

import com.wen.base.enums.ResultCode;

/**
 * @Author wsw
 * @Date 2023/2/3 15:02
 **/
public class PermissionForbiddenException extends BusinessException {
    public PermissionForbiddenException() {
    }

    public PermissionForbiddenException(Object data) {
        super.data = data;
    }

    public PermissionForbiddenException(ResultCode resultCode) {
        super(resultCode);
    }

    public PermissionForbiddenException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public PermissionForbiddenException(String msg) {
        super(msg);
    }
}
