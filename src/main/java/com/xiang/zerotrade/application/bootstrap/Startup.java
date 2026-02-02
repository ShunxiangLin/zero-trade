package com.xiang.zerotrade.application.bootstrap;

import com.xiang.zerotrade.application.handler.EventHandler;
import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.kline.Kline;
import com.xiang.zerotrade.domain.market.pair.Pair;
import com.xiang.zerotrade.domain.market.subscription.MarketSubscription;
import com.xiang.zerotrade.infrastructure.bus.impl.LocalEventBus;
import com.xiang.zerotrade.infrastructure.market.cache.PairCache;
import com.xiang.zerotrade.infrastructure.market.clent.KlineRestClient;
import com.xiang.zerotrade.infrastructure.market.webSocket.KlineWebSocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.xiang.zerotrade.domain.market.enums.StreamType.KLINE;

/**
 * @author shunxiang.lin
 * @date 29/01/2026
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class Startup {

    private final List<EventHandler> handlerList;
    private final LocalEventBus bus;
    private final KlineWebSocket klineWebSocket;
    private final PairCache pairCache;
    private final KlineRestClient klineRestClient;

    /**
     * 项目启动初始化程序
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        // 加载DB Pair
        loadPair();

        // 注册所有事件 handler
        eventHandlerRegistrar();

        // 启动行情订阅
        klineSubscriptionStart();

        // 初始化数据
        initMarketData();

    }

    public void initMarketData() {
        // 是否要初始化

        // 初始化数据
        Pair pair = pairCache.getPairBySymbol("BTCUSDT", MarketType.FUTURES_USDT);

        long end = System.currentTimeMillis();
        long start = end - 60_000 * 10;

        List<Kline> klines = klineRestClient.fetchKines(pair, start, end);

        log.info("======== REST KLINE TEST ========");
        klines.stream().limit(3).forEach(k ->
                log.info(
                        "pairId={} openTime={} open={}",
                        k.pairId(),
                        k.openTime(),
                        k.openPrice()
                )
        );
        log.info("total klines = {}", klines.size());
        log.info("================================");
        // 发送通知
    }


    /**
     * 开始订阅kline webSocket
     * 加载数据库里所有币种 进行订阅
     */
    public void klineSubscriptionStart() {
        List<MarketSubscription> list = pairCache.getAll().stream()
                .filter(Pair::isTradable)
                .map(pair -> new MarketSubscription(
                        pair.getMarketType(),
                        pair.getSymbol(),
                        KLINE
                )).toList();

        klineWebSocket.klineSubscriptionStart(list);
    }

    /**
     * 从数据库里加载所有 Pair币种
     */
    private void loadPair() {
        pairCache.loadAll();
        pairCache.logsPair();
    }


    /**
     * 加载所有handler进行订阅
     */
    private void eventHandlerRegistrar() {
        for (EventHandler handler : handlerList) {
            bus.subscribe(handler);
        }
        bus.logSubscriptions();
    }


}
