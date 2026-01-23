package com.xiang.zerotrade.domain.market.pairRule;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */

public record FeeRule(
        BigDecimal makerFeeRate,
        BigDecimal takerFeeRate
) {
}
