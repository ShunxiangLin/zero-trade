package com.xiang.zerotrade.domain.market.pair;

import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.enums.PairStatus;

/**
 * @author linshunxiang
 */
public record Pair(
        long id,
        MarketType marketType,
        String symbol,
        String base,
        String quote,
        PairStatus status,
        TradeRule tradeRule,
        ContractRule contractRule,
        Long onboardDate,
        Long deliveryDate,
        FeeRule feeRule
) {
}
