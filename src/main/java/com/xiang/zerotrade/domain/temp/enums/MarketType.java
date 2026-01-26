package com.xiang.zerotrade.domain.temp.enums;

/**
 * @author linshunxiang
 */
public enum MarketType {
    SPOT(false),
    PERPETUAL(true),
    DELIVERY(true);

    private final boolean contract;

    MarketType(boolean contract) {
        this.contract = contract;
    }

    public boolean isContract() {
        return contract;
    }

    public boolean isSpot() {
        return this == SPOT;
    }

}
