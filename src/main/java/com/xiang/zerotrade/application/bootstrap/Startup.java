package com.xiang.zerotrade.application.bootstrap;

import com.xiang.zerotrade.domain.market.pair.Pair;
import com.xiang.zerotrade.infrastructure.market.pair.PairCache;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shunxiang.lin
 * @date 29/01/2026
 */
@Component
@RequiredArgsConstructor
public class Startup {

    private final EventHandlerRegistrar eventHandlerRegistrar;
    private final MarketStartup marketStartup;
    private final PairCache pairCache;

    /**
     * 项目启动初始化程序
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onReady(){
        // 注册所有事件 handler
        eventHandlerRegistrar.registerAll();

        // 加载DB Pair
        pairCache.loadAll();

        // 启动行情订阅
        marketStartup.klineSubscriptionStart();

    }
}
