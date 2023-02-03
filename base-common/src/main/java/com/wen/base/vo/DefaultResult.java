package com.wen.base.vo;

import com.wen.base.dto.ResultDTO;
import com.wen.base.enums.ResultCode;

/**
 * @Author wsw
 * @Date 2023/2/2 15:11
 **/
public class DefaultResult<T> implements Result {
    private Integer code;
    private String msg;
    private T data;

    public DefaultResult() {
    }

    public DefaultResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static DefaultResult success() {
        DefaultResult result = new DefaultResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> DefaultResult<T> success(T data) {
        DefaultResult<T> result = new DefaultResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static DefaultResult failure(ResultCode resultCode) {
        DefaultResult result = new DefaultResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> DefaultResult<T> failure(ResultCode resultCode, T data) {
        DefaultResult<T> result = new DefaultResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static <T> DefaultResult<T> of(ResultDTO<T> dto) {
        DefaultResult<T> result = new DefaultResult();
        boolean flag = dto.getSuccess();
        String msg = dto.getMsg() != null ? dto.getMsg() : (flag ? "操作成功" : "操作失败");
        result.setCode(flag ? ResultCode.SUCCESS.code() : ResultCode.SYSTEM_INNER_ERROR.code());
        result.setMsg(msg);
        result.setData(dto.getData());
        return result;
    }

    public static DefaultResult failure(String message) {
        DefaultResult result = new DefaultResult();
        result.setCode(ResultCode.PARAM_IS_INVALID.code());
        result.setMsg(message);
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
