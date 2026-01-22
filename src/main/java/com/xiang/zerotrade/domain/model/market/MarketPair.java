package com.xiang.zerotrade.domain.model.market;

/**
 * @param marketType 市场类型 SPOT/PERPETUAL/DELIVERY
 * @param pair 币种对
 * @author linshunxiang
 */
public record MarketPair(
        MarketType marketType,
        Pair pair,
        PairRule pairRule
) {

}
