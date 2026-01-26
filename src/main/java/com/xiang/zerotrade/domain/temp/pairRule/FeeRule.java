package com.xiang.zerotrade.domain.temp.pairRule;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */

public record FeeRule(
        BigDecimal makerFeeRate,
        BigDecimal takerFeeRate
) {
}
