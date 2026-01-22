package com.xiang.zerotrade.common.exception;

/**
 * @author linshunxiang
 */
public enum ErrorCode {
    // ======= temp ======
    INTERNAL_ERROR("ZT-0000", "Internal error"),
    REMOTE_TIMEOUT("ZT-1001", "Remote timeout"),
    RATE_LIMITED("ZT-1004", "Rate limited"),
    RISK_REJECTED("ZT-2001", "Risk rejected"),

    // ===== 通用 =====

    // ===== 外部依赖/网络 ====

    // ===== 交易域 ====
    INSUFFICIENT_BALANCE("ZT-2001", "账号余额不足");


    private final String code;
    private final String defaultMessage;

    ErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String code() {
        return code;
    }

    public String defaultMessage() {
        return defaultMessage;
    }
}
