package com.xiang.zerotrade.domain.market.subscription;

import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.enums.StreamType;

/**
 * @author shunxiang.lin
 * @date 29/01/2026
 */
public record MarketSubscription(
        MarketType marketType,
        String symbol,
        StreamType streamType
) {
}
