package com.xiang.zerotrade.domain.market.kline;

import com.xiang.zerotrade.common.until.FormatUtil;
import com.xiang.zerotrade.domain.market.enums.Interval;
import com.xiang.zerotrade.domain.market.pair.Pair;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */

@Builder
public record Kline(
        int pairId,
        Interval interval,
        long openTime,
        long closeTime,
        BigDecimal openPrice,
        BigDecimal closePrice,
        BigDecimal highPrice,
        BigDecimal lowPrice,
        BigDecimal volume
) {
    @Override
    public String toString() {
        return new StringBuilder(256)
                .append('{')
                .append("\"pairId\":").append(pairId).append(',')
                .append("\"openTime\":\"").append(FormatUtil.formatTimestamp(openTime)).append("\",")
                .append("\"closeTime\":\"").append(FormatUtil.formatTimestamp(closeTime)).append("\",")
                .append("\"openPrice\":").append(openPrice).append(',')
                .append("\"closePrice\":").append(closePrice).append(',')
                .append("\"highPrice\":").append(highPrice).append(',')
                .append("\"lowPrice\":").append(lowPrice).append(',')
                .append("\"volume\":").append(volume)
                .append('}')
                .toString();
    }
}
