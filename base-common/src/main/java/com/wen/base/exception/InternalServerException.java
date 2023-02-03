package com.wen.base.exception;

/**
 * @Author wsw
 * @Date 2023/2/2 15:32
 **/
public class InternalServerException extends BusinessException {
    public InternalServerException() {
    }

    public InternalServerException(String msg) {
        super(msg);
    }
}
