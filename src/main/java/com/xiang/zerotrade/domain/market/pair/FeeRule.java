package com.xiang.zerotrade.domain.market.pair;

import com.xiang.zerotrade.common.until.FormatUtil;

import java.math.BigDecimal;

/**
 * @param makerFeeRate Maker 手续费率，例如 0.001 (0.1%)
 * @param takerFeeRate Taker 手续费率，例如 0.002 (0.2%)
 * @author linshunxiang
 */
public record FeeRule(
        BigDecimal makerFeeRate,
        BigDecimal takerFeeRate
) {
    @Override
    public String toString() {
        return new StringBuilder(256)
                .append('{')
                .append("\"makerFeeRate\":").append(FormatUtil.formatPercent(makerFeeRate)).append(',')
                .append("\"takerFeeRate\":").append(FormatUtil.formatPercent(takerFeeRate))
                .append('}')
                .toString();
    }
}
