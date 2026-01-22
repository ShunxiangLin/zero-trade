package com.xiang.zerotrade.common.exception;

/**
 * @author linshunxiang
 */
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode, String message, Throwable cause) {
        super(message != null ? message : errorCode.defaultMessage(), cause);
        this.errorCode = errorCode;
    }

    public AppException(ErrorCode errorCode, String message) {
        this(errorCode, message, null);
    }

    public AppException(ErrorCode errorCode) {
        this(errorCode, null, null);
    }

    public ErrorCode errorCode() {
        return errorCode;
    }
}
