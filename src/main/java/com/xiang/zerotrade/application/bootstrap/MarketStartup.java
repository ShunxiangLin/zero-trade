package com.xiang.zerotrade.application.bootstrap;

import com.xiang.zerotrade.domain.market.subscription.MarketSubscription;
import com.xiang.zerotrade.infrastructure.market.MarketDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.xiang.zerotrade.domain.market.enums.MarketType.*;
import static com.xiang.zerotrade.domain.market.enums.StreamType.KLINE;

/**
 * @author shunxiang.lin
 * @date 30/01/2026
 */
@Component
@RequiredArgsConstructor
public class MarketStartup {

    private final MarketDataSource marketDataSource;

    /**
     * 开始订阅kline webSocket
     */
    public void klineSubscriptionStart(){
        List<MarketSubscription> subscriptions = getSubscriptions();
        marketDataSource.klineSubscriptionStart(subscriptions);
    }


    /**
     * 订阅哪些币种对的kline信息
     */
    private List<MarketSubscription> getSubscriptions() {
        // TODO 获取缓存里的所有数据? or 支持参数化配置
        return List.of(
                // 现货
                new MarketSubscription(SPOT, "BTCUSDT", KLINE),
                new MarketSubscription(SPOT, "ETHUSDT", KLINE),

                // U 本位合约
                new MarketSubscription(FUTURES_USDT, "BTCUSDT", KLINE)
        );
    }


}
