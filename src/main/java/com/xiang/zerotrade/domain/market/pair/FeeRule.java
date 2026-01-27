package com.xiang.zerotrade.domain.market.pair;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */

public record FeeRule(
        BigDecimal makerFeeRate,
        BigDecimal takerFeeRate
) {
}
