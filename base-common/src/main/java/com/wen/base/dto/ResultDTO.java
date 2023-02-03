package com.wen.base.dto;

/**
 * @Author wsw
 * @Date 2023/2/2 15:18
 **/
public class ResultDTO<T> {
    private Boolean success;
    private String msg;
    private T data;

    public ResultDTO() {
        this.success = false;
    }

    public ResultDTO(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResultDTO(Boolean success, T data) {
        this.success = success != null ? success : false;
        this.data = data;
    }

    public ResultDTO(Boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public ResultDTO<String> dataToString() {
        ResultDTO<String> resultDTO = new ResultDTO();
        resultDTO.setSuccess(this.success);
        resultDTO.setMsg(this.getMsg());
        String data = null;
        if (this.data != null) {
            data = String.valueOf(this.data);
        }

        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> success() {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(true);
        result.setMsg("操作成功");
        return result;
    }

    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(true);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static <T> ResultDTO<T> successData(T data) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(true);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static <T> ResultDTO<T> success(String msg) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultDTO<T> success(T data, String msg) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(true);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> ResultDTO<T> failure() {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(false);
        result.setMsg("操作失败");
        return result;
    }

    public static <T> ResultDTO<T> failure(T data) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(false);
        result.setMsg("操作失败");
        result.setData(data);
        return result;
    }

    public static <T> ResultDTO<T> failureData(T data) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(false);
        result.setMsg("操作失败");
        result.setData(data);
        return result;
    }

    public static <T> ResultDTO<T> failure(String msg) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultDTO<T> failure(T data, String msg) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(false);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public boolean getSuccess() {
        return this.success != null ? this.success : false;
    }

    public void setSuccess(Boolean success) {
        this.success = success != null ? success : false;
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
