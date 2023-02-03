package com.wen.base.web;

import com.wen.base.enums.ResultCode;
import com.wen.base.exception.BusinessException;
import com.wen.base.exception.FeignInvokeException;
import com.wen.base.vo.DefaultErrorResult;
import com.wen.base.vo.DefaultResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @Author wsw
 * @Date 2023/2/2 15:03
 **/
@Slf4j
public class MyControllerSupport {

    public MyControllerSupport() {
    }

    @ExceptionHandler({BindException.class})
    protected DefaultResult<String> bindException(HttpServletRequest request, BindException e) {
        log.error("[{}], uri:[{}], caused by:", new Object[]{e.getClass().getName(), request.getRequestURI(), e});
        String message = (String) e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return new DefaultResult(ResultCode.PARAM_IS_INVALID.code(), message, (Object) null);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public DefaultResult<String> constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException e) {
        log.error("[{}], uri:[{}], caused by:", new Object[]{e.getClass().getName(), request.getRequestURI(), e});
        String message = (String) e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return new DefaultResult(ResultCode.PARAM_IS_INVALID.code(), message, (Object) null);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public DefaultResult<String> methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("[{}], uri:[{}], caused by:", new Object[]{e.getClass().getName(), request.getRequestURI(), e});
        String message = (String) e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return new DefaultResult(ResultCode.PARAM_IS_INVALID.code(), message, (Object) null);
    }

    @ExceptionHandler({BusinessException.class})
    public DefaultErrorResult businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        log.error("[{}], uri:[{}], caused by:", new Object[]{e.getClass().getName(), request.getRequestURI(), e});
        String message = e.getMessage();
        if (StringUtils.isNotBlank(message)) {
            //message = I18NUtils.getMessage("errmsg." + DigestUtils.md5DigestAsHex(message.getBytes(StandardCharsets.UTF_8)), message);
            e.setMessage(message);
        }

        return DefaultErrorResult.failure(e);
    }

    @ExceptionHandler({FeignInvokeException.class})
    public DefaultResult<Object> feignInvokeExceptionHandler(HttpServletRequest request, FeignInvokeException e) {
        log.error("[{}], uri:[{}], caused by:", new Object[]{e.getClass().getName(), request.getRequestURI(), e});
        return new DefaultResult(e.getCode(), e.getMessage(), e.getData());
    }

    @ExceptionHandler({Exception.class})
    protected DefaultResult<String> exception(Exception e) {
        log.error("[{}], caused by:", e.getClass().getName(), e);
        return new DefaultResult(ResultCode.SYSTEM_INNER_ERROR.code(), e.getMessage(), e.toString());
    }
}
