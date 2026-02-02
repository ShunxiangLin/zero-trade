package com.xiang.zerotrade.application.handler;

import com.xiang.zerotrade.domain.event.EventType;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.domain.event.payloadImpl.MarketTickPayload;
import com.xiang.zerotrade.domain.event.payloadImpl.StrategySignalPayload;
import com.xiang.zerotrade.domain.market.kline.Kline;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import com.xiang.zerotrade.infrastructure.logging.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author linshunxiang
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MarketTickHandler implements EventHandler {

    private final Executor sideEffectExecutor;
    private final EventBus bus;

    @Override
    public EventType eventType() {
        return EventType.MARKET_TICK;
    }

    @Override
    public String name() {
        return "K线处理器";
    }

    @Override
    public void handle(Event event) {
        if (!(event.payload() instanceof MarketTickPayload payload)) {
            throw new IllegalStateException("MARKET_TICK event payload is not MarketTickPayload");
        }

        Kline kline = payload.kline();

        System.out.println(kline);

        // 异步保存Kline到数据库
        sideEffectExecutor.execute(() -> {
            try {
                // db 操作
            } catch (Exception e) {
                log.error("数据库操作失败", e);
            }
        });

        // 刷新缓存
        System.out.println("刷新缓存");

        // 推送给strategySignalHandler
        System.out.println( "推送");

    }


}