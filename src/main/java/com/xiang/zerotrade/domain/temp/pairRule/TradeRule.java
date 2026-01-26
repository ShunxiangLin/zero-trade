package com.xiang.zerotrade.domain.temp.pairRule;

import lombok.Builder;

import java.math.BigDecimal;

/**
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
){

}
