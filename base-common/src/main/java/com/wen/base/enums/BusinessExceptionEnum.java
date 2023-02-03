package com.wen.base.enums;

import com.wen.base.exception.*;
import org.springframework.http.HttpStatus;

/**
 * @Author wsw
 * @Date 2023/2/2 15:24
 **/
public enum BusinessExceptionEnum {
    PARAMETER_INVALID(ParameterInvalidException.class, HttpStatus.BAD_REQUEST, ResultCode.PARAM_IS_INVALID),
    NOT_FOUND(DataNotFoundException.class, HttpStatus.NOT_FOUND, ResultCode.DATA_NONE),
    //METHOD_NOT_ALLOWED(MethodNotAllowException.class, HttpStatus.METHOD_NOT_ALLOWED, ResultCode.INTERFACE_ADDRESS_INVALID),
    CONFLICT(DataConflictException.class, HttpStatus.CONFLICT, ResultCode.DATA_ALREADY_EXISTED),
    //UNAUTHORIZED(UserNotLoginException.class, HttpStatus.UNAUTHORIZED, ResultCode.USER_NOT_LOGGED_IN),
    //FORBIDDEN(PermissionForbiddenException.class, HttpStatus.FORBIDDEN, ResultCode.PERMISSION_NO_ACCESS),
    //REMOTE_ACCESS_ERROR(RemoteAccessException.class, HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.INTERFACE_OUTSIDE_INVOKE_ERROR),
    INTERNAL_SERVER_ERROR(InternalServerException.class, HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.SYSTEM_INNER_ERROR);

    private Class<? extends BusinessException> eClass;
    private HttpStatus httpStatus;
    private ResultCode resultCode;

    private BusinessExceptionEnum(Class<? extends BusinessException> eClass, HttpStatus httpStatus, ResultCode resultCode) {
        this.eClass = eClass;
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
    }

    public Class<? extends BusinessException> getEClass() {
        return this.eClass;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }

    public static boolean isSupportHttpStatus(int httpStatus) {
        BusinessExceptionEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            BusinessExceptionEnum exceptionEnum = var1[var3];
            if (exceptionEnum.httpStatus.value() == httpStatus) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSupportException(Class<?> z) {
        BusinessExceptionEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            BusinessExceptionEnum exceptionEnum = var1[var3];
            if (exceptionEnum.eClass.equals(z)) {
                return true;
            }
        }

        return false;
    }

    public static BusinessExceptionEnum getByHttpStatus(HttpStatus httpStatus) {
        if (httpStatus == null) {
            return null;
        } else {
            BusinessExceptionEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                BusinessExceptionEnum exceptionEnum = var1[var3];
                if (httpStatus.equals(exceptionEnum.httpStatus)) {
                    return exceptionEnum;
                }
            }

            return null;
        }
    }

    public static BusinessExceptionEnum getByEClass(Class<? extends BusinessException> eClass) {
        if (eClass == null) {
            return null;
        } else {
            BusinessExceptionEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                BusinessExceptionEnum exceptionEnum = var1[var3];
                if (eClass.equals(exceptionEnum.eClass)) {
                    return exceptionEnum;
                }
            }

            return null;
        }
    }

}
