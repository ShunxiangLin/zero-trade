package com.xiang.zerotrade.infrastructure.market;

import com.xiang.zerotrade.domain.market.pair.Pair;

/**
 * @author linshunxiang
 */

public interface MarketDataPublisher {
    void publishTick(String msg);
}
