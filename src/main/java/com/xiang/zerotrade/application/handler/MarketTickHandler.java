package com.xiang.zerotrade.application.handler;

import com.xiang.zerotrade.domain.enums.EventType;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import com.xiang.zerotrade.infrastructure.logging.JsonLog;
import com.xiang.zerotrade.infrastructure.logging.LogKeys;
import com.xiang.zerotrade.infrastructure.logging.Logs;
import com.xiang.zerotrade.infrastructure.logging.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author linshunxiang
 */
@Component
@RequiredArgsConstructor
public class MarketTickHandler implements EventHandler<Event> {

    private final EventBus bus;

    @Override
    public EventType eventType() {
        return EventType.MARKET_TICK;
    }

    @Override
    public String name() {
        return "MarketTickHandler#handle";
    }

    @Override
    public void handle(Event event) {
        // 👇 P0：直接把信号“翻译”为下单请求
        Event strategyEvent = new Event(
                Trace.newEventId(),
                EventType.ORDER_REQUEST,
                System.currentTimeMillis(),
                event.symbol(),
                "side=BUY;qty=1;reason=strategySignal"
        );

        bus.publish(EventType.ORDER_REQUEST, strategyEvent);


    }


}