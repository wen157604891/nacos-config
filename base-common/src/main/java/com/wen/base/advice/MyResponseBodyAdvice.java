package com.wen.base.advice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wen.base.annotation.FeignApiController;
import com.wen.base.annotation.IgnoreResponseAdvice;
import com.wen.base.dto.ResultDTO;
import com.wen.base.utils.PageUtils;
import com.wen.base.vo.DefaultErrorResult;
import com.wen.base.vo.DefaultResult;
import com.wen.base.web.MyControllerSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @Author wsw
 * @Date 2023/2/2 16:45
 **/
@RestControllerAdvice
@Slf4j
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    public MyResponseBodyAdvice() {
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Class<?> declaringClass = methodParameter.getDeclaringClass();
        if (!MyControllerSupport.class.isAssignableFrom(declaringClass)) {
            return false;
        } else if (declaringClass.isAnnotationPresent(FeignApiController.class)) {
            return false;
        } else if (declaringClass.isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        } else {
            Method method = methodParameter.getMethod();
            if (method == null) {
                return false;
            } else {
                return !method.isAnnotationPresent(IgnoreResponseAdvice.class);
            }
        }
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("请求返回数据类型class={}", object.getClass().getName());
        if (object instanceof DefaultResult) {
            return object;
        } else if (object instanceof DefaultErrorResult) {
            DefaultErrorResult defaultErrorResult = (DefaultErrorResult)DefaultErrorResult.class.cast(object);
            return new DefaultResult(defaultErrorResult.getCode(), defaultErrorResult.getMessage(), defaultErrorResult.getErrors());
        } else if (object instanceof ResultDTO) {
            return DefaultResult.of((ResultDTO)ResultDTO.class.cast(object));
        } else {
            return object instanceof IPage ? DefaultResult.success(PageUtils.buildPage((IPage)IPage.class.cast(object))) : DefaultResult.success(object);
        }
    }
}
