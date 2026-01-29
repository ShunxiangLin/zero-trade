package com.xiang.zerotrade.infrastructure.market.feed;

import com.xiang.zerotrade.common.until.WebSocketHelper;
import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.subscription.MarketSubscription;
import com.xiang.zerotrade.infrastructure.market.MarketDataPublisher;
import com.xiang.zerotrade.infrastructure.market.MarketDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linshunxiang
 */
@Component
@RequiredArgsConstructor
public class BinanceMarketWebSocketImpl implements MarketDataSource {

    private final MarketDataPublisher publisher;

    @Override
    public void klineSubscriptionStart(List<MarketSubscription> subscriptionList) {
        if (subscriptionList.isEmpty()) return;

        Map<MarketType, List<MarketSubscription>> groupByMarketType = subscriptionList.stream()
                .collect(Collectors.groupingBy(MarketSubscription::marketType));

        for (Map.Entry<MarketType, List<MarketSubscription>> entry : groupByMarketType.entrySet()) {
            String url = buildCombinedKlineWsUrl(entry.getKey(), entry.getValue());

            System.out.println("url = " + url);

            WebSocketHelper.subscribe(url, publisher::publishTick);
        }


        WebSocketHelper.subscribe("wss://stream.binance.com:9443/ws/btcusdt@kline_1m",
                msg -> publisher.publishTick(msg)
        );
    }

    private String buildCombinedKlineWsUrl(MarketType marketType, List<MarketSubscription> subscriptionList) {
        String streams = subscriptionList.stream()
                .map(s -> s.symbol().toLowerCase() + "@kline_1m").collect(Collectors.joining("/"));
        String base = switch (marketType) {
            case SPOT -> "wss://stream.binance.com:9443/stream?streams=";
            case FUTURES_USDT -> "wss://fstream.binance.com/stream?streams=";
            case FUTURES_COIN -> "wss://dstream.binance.com/stream?streams=";
        };

        return base + streams;

    }


    @Override
    public void stop() {
        WebSocketHelper.close();
    }
}
