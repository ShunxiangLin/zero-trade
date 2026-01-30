package com.xiang.zerotrade.application.bootstrap;

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
    private final MarketStartup marketStartup;

    /**
     * 项目启动初始化程序
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onReady(){
        // 注册所有事件处理器
        eventHandlerRegistrar.registerAll();

        // 启动行情订阅
        marketStartup.klineSubscriptionStart();



    }
}
