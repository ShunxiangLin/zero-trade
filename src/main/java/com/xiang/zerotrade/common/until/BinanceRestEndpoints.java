package com.xiang.zerotrade.common.until;

import com.xiang.zerotrade.domain.market.enums.MarketType;

/**
 * @author linshunxiang
 */

public class BinanceRestEndpoints {
    private BinanceRestEndpoints() {
    }

    public static String baseUrl(MarketType type) {
        return switch (type) {
            case SPOT -> "https://api.binance.com";
            case FUTURES_USDT -> "https://fapi.binance.com";
            case FUTURES_COIN -> "https://dapi.binance.com";
        };
    }

    public static String klinePath(MarketType type) {
        return switch (type) {
            case SPOT -> "/api/v3/klines";
            case FUTURES_USDT -> "/fapi/v1/klines";
            case FUTURES_COIN -> "/dapi/v1/klines";
        };
    }
}
