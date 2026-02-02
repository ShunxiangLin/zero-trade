package com.xiang.zerotrade.infrastructure.market;

import com.xiang.zerotrade.domain.market.subscription.MarketSubscription;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author linshunxiang
 */

public interface MarketDataSource {
    void klineSubscriptionStart(List<MarketSubscription> subscriptionList);

    void stop();
}
