package com.xiang.zerotrade.domain.market.enums;

/**
 * @author linshunxiang
 */

public enum PairStatus {
    NOT_TRADING(0),
    TRADING(1),
    UNKNOWN(2);

    private final int code;

    PairStatus(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public static PairStatus fromCode(int code) {
        return switch (code) {
            case 0 -> NOT_TRADING;
            case 1 -> TRADING;
            default -> UNKNOWN;
        };
    }

}
