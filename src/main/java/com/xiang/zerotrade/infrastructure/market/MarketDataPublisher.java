package com.xiang.zerotrade.infrastructure.market;

import com.xiang.zerotrade.domain.market.enums.MarketType;

/**
 * @author linshunxiang
 */

public interface MarketDataPublisher {
    void publishTick(MarketType marketType, String msg);
}
