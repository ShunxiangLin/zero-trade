package com.xiang.zerotrade.domain.model.market;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */

public record FeeRule(
        BigDecimal makerFeeRate,
        BigDecimal takerFeeRate

) {
}
