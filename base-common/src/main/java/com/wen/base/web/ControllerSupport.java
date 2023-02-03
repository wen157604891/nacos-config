package com.wen.base.web;

import com.wen.base.vo.RESTfulResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wsw
 * @Date 2023/2/2 17:01
 **/
public class ControllerSupport {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final ThreadLocal<RESTfulResponseModel> restfulResponseModelThreadLocal = ThreadLocal.withInitial(() -> {
        return new RESTfulResponseModel();
    });

    public ControllerSupport() {
    }

    protected RESTfulResponseModel renderRESTfulResponseModel(Object data, Object... extendData) {
        RESTfulResponseModel restfulResponseModel = (RESTfulResponseModel) this.restfulResponseModelThreadLocal.get();
        restfulResponseModel.setResponseData(data);
        restfulResponseModel.setResponseExtendData(extendData != null && extendData.length > 0 ? extendData[0] : null);
        restfulResponseModel.setResponseResult(RESTfulResponseModel.ResponseResult.SUCCESS);
        restfulResponseModel.setResponseMsg("操作成功");
        return restfulResponseModel;
    }

    @ExceptionHandler
    protected RESTfulResponseModel handleException(HttpServletRequest request, Exception e) {
        RESTfulResponseModel restfulResponseModel = new RESTfulResponseModel();
        restfulResponseModel.setResponseResult(RESTfulResponseModel.ResponseResult.ERROR);
        restfulResponseModel.setResponseMsg("数据异常");
        restfulResponseModel.setResponseException(e);
        this.logger.error(e.getMessage(), e);
        return restfulResponseModel;
    }

    protected RESTfulResponseModel getRESTfulResponseModel() {
        return (RESTfulResponseModel) this.restfulResponseModelThreadLocal.get();
    }

    public Logger getLogger() {
        return this.logger;
    }
}
