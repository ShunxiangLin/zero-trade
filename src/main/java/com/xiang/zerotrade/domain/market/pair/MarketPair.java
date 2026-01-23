package com.xiang.zerotrade.domain.market.pair;

import com.xiang.zerotrade.domain.market.enums.MarketType;

/**
 * 可交易的币种对
 * @param marketType 市场类型 SPOT/PERPETUAL/DELIVERY
 * @param pair       币种对
 * @author linshunxiang
 */
public record MarketPair(
        MarketType marketType,
        Pair pair
) {
    public String display() {
        return marketType + ": " + pair.baseAsset() + pair.quoteAsset();
    }

    public boolean isSpot() {
        return marketType.isSpot();
    }
}
