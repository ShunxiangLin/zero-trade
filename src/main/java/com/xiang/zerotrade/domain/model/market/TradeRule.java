package com.xiang.zerotrade.domain.model.market;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */

public record TradeRule(
        // 下单精度
        BigDecimal tickSize,
        BigDecimal stepSize,
        // 下单门槛
        BigDecimal minPrice,
        BigDecimal minQty,
        BigDecimal minNotional
){

}
