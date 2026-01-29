package com.xiang.zerotrade.application.bootstrap;

import com.xiang.zerotrade.infrastructure.market.MarketDataSource;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author shunxiang.lin
 * @date 29/01/2026
 */
@Component
@RequiredArgsConstructor
public class Startup {

    private final EventHandlerRegistrar eventHandlerRegistrar;
    private final MarketDataSource marketDataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady(){
        marketDataSource.start();

        eventHandlerRegistrar.registerAll();

    }
}
