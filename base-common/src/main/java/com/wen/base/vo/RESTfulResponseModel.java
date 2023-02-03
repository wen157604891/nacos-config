package com.wen.base.vo;

/**
 * @Author wsw
 * @Date 2023/2/2 17:02
 **/
public class RESTfulResponseModel {
    private static final String RESPONSE_PATTERN = "responsePattern";
    private Object responseData;
    private Object responseExtendData;
    private RESTfulResponseModel.ResponseResult responseResult;
    private String responseMsg;
    private Exception responseException;
    private String responsePattern;
    private Boolean responseLoginStatus = true;

    public RESTfulResponseModel() {
    }

    public RESTfulResponseModel(Object responseData, RESTfulResponseModel.ResponseResult responseResult) {
        this.responseData = responseData;
        this.responseResult = responseResult;
    }

    public String getResponseMsg() {
        return this.responseMsg;
    }

    public RESTfulResponseModel setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
        return this;
    }

    public String getResponseException() {
        return "debug".equals(this.responsePattern) ? this.responseException.toString() : null;
    }

    public String getResponseExceptionMessage() {
        return "debug".equals(this.responsePattern) ? this.responseException.getMessage() : null;
    }

    public StackTraceElement[] getResponseExceptionStackTraceElement() {
        return "debug".equals(this.responsePattern) ? this.responseException.getStackTrace() : null;
    }

    public RESTfulResponseModel setResponseException(Exception responseException) {
        this.responseException = responseException;
        return this;
    }

    public Object getResponseData() {
        return this.responseData;
    }

    public RESTfulResponseModel setResponseData(Object responseData) {
        this.responseData = responseData;
        return this;
    }

    public RESTfulResponseModel successResponse(Object responseData, String... responseOtherInfo) {
        this.responseResult = RESTfulResponseModel.ResponseResult.SUCCESS;
        this.responseData = responseData;
        if (responseOtherInfo != null && responseOtherInfo.length > 0) {
            this.responseMsg = responseOtherInfo[0];
        } else {
            this.responseMsg = "操作成功";
        }

        return this;
    }

    public RESTfulResponseModel errorResponse(String responseMsg) {
        this.responseResult = RESTfulResponseModel.ResponseResult.ERROR;
        this.responseMsg = responseMsg;
        return this;
    }

    public RESTfulResponseModel.ResponseResult getResponseResult() {
        return this.responseResult;
    }

    public RESTfulResponseModel setResponseResult(RESTfulResponseModel.ResponseResult responseResult) {
        this.responseResult = responseResult;
        return this;
    }

    public RESTfulResponseModel setResponsePattern(String responsePattern) {
        this.responsePattern = responsePattern;
        return this;
    }

    public Object getResponseExtendData() {
        return this.responseExtendData;
    }

    public Boolean getResponseLoginStatus() {
        return this.responseLoginStatus;
    }

    public void setResponseLoginStatus(Boolean responseLoginStatus) {
        this.responseLoginStatus = responseLoginStatus;
    }

    public RESTfulResponseModel setResponseExtendData(Object responseExtendData) {
        this.responseExtendData = responseExtendData;
        return this;
    }

    public static enum ResponseResult {
        SUCCESS,
        ERROR,
        UNAUTHORIZED,
        ACCESS_DENIED,
        INVALID_REQUEST,
        INVALID_TOKEN,
        INVALID_CLIENT,
        INVALID_GRANT;

        private ResponseResult() {
        }
    }
}
