package com.wen.base.exception;

/**
 * @Author wsw
 * @Date 2023/2/3 15:01
 **/
public class UserNotLoginException extends BusinessException {
    public UserNotLoginException() {
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }
}
