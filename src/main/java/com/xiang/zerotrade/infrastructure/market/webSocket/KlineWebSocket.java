package com.xiang.zerotrade.infrastructure.market.webSocket;

import com.xiang.zerotrade.common.until.WebSocketHelper;
import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.subscription.MarketSubscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linshunxiang
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KlineWebSocket {

    private final KlinePublisher publisher;

    public void klineSubscriptionStart(List<MarketSubscription> subscriptionList) {
        if (subscriptionList.isEmpty()) {
            return;
        }

        // 根据市场 分组发送不同的URL
        Map<MarketType, List<MarketSubscription>> groupByMarketType = subscriptionList.stream()
                .collect(Collectors.groupingBy(MarketSubscription::marketType));

        // 拼接URL & 订阅
        for (Map.Entry<MarketType, List<MarketSubscription>> entry : groupByMarketType.entrySet()) {
            String url = buildCombinedKlineWsUrl(entry.getKey(), entry.getValue());
            WebSocketHelper.subscribe(url, msg -> publisher.publishTick(entry.getKey(), msg));
            log.info("【初始化】订阅 {} WebSocket成功, type={}, url={}", entry.getValue().getFirst().streamType(), entry.getKey().name(), url);
        }
    }

    private String buildCombinedKlineWsUrl(MarketType marketType, List<MarketSubscription> subscriptionList) {
        String subscriptType = switch (subscriptionList.getFirst().streamType()) {
            case KLINE -> "@kline_1m";
        };
        String streams = subscriptionList.stream()
                .map(s -> s.symbol().toLowerCase() + subscriptType).collect(Collectors.joining("/"));
        String base = switch (marketType) {
            case SPOT -> "wss://stream.binance.com:9443/stream?streams=";
            case FUTURES_USDT -> "wss://fstream.binance.com/stream?streams=";
            case FUTURES_COIN -> "wss://dstream.binance.com/stream?streams=";
        };
        return base + streams;
    }


    public void stop() {
        WebSocketHelper.close();
    }
}
