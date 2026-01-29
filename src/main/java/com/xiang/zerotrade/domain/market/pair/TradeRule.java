package com.xiang.zerotrade.domain.market.pair;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * @param tickSize     价格最小变动单位（Price Tick），用于限制报价精度
 * @param stepSize     数量最小变动单位（Quantity Step），用于限制下单数量精度
 * @param minPrice     允许的最小下单价格
 * @param minQty       允许的最小下单数量
 * @param maxQty       允许的最大下单数量（部分交易对可能为空）
 * @param minNotional  最小名义金额（price * quantity），常用于防止过小订单
 * @param maxNotional  最大名义金额（部分交易对可能为空）
 * @author linshunxiang
 */
@Builder
public record TradeRule(
        // 下单精度
        BigDecimal tickSize,
        BigDecimal stepSize,
        // 下单门槛
        BigDecimal minPrice,
        BigDecimal minQty,
        BigDecimal maxQty,
        BigDecimal minNotional,
        BigDecimal maxNotional
) {

    @Override
    public String toString() {
        return new StringBuilder(256)
                .append('{')
                .append("\"tickSize\":").append(tickSize).append(',')
                .append("\"stepSize\":").append(stepSize).append(',')
                .append("\"minPrice\":").append(minPrice).append(',')
                .append("\"minQty\":").append(minQty).append(',')
                .append("\"maxQty\":").append(maxQty).append(',')
                .append("\"minNotional\":").append(minNotional).append(',')
                .append("\"maxNotional\":").append(maxNotional)
                .append('}')
                .toString();
    }

}
