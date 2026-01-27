package com.xiang.zerotrade.domain.market.kline;

import com.xiang.zerotrade.domain.market.pair.Pair;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */

public record Kline(
        long pairId,
        long openTime,
        long closeTime,
        BigDecimal openPrice,
        BigDecimal closePrice,
        BigDecimal highPrice,
        BigDecimal lowPrice,
        BigDecimal volume
) {
}
