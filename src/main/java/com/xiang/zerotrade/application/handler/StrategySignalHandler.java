package com.xiang.zerotrade.application.handler;

import com.xiang.zerotrade.domain.event.EventType;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import com.xiang.zerotrade.infrastructure.logging.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author linshunxiang
 */
@Component
@RequiredArgsConstructor
public class StrategySignalHandler implements EventHandler<Event> {

    private final EventBus bus;

    @Override
    public EventType eventType() {
        return EventType.STRATEGY_SIGNAL;
    }

    @Override
    public String name() {
        return "StrategySignalHandler:handler";
    }

    @Override
    public void handle(Event event) {
//        // P0先不下单，只打印模拟：
//        // 后面你要做 ORDER_REQUEST 时，就在这里 bus.publish(ORDER_REQUEST, orderReq)
//
//        // 👇 P0：直接把信号“翻译”为下单请求
//        Event orderReq = new Event(
//                Trace.newEventId(),
//                EventType.ORDER_REQUEST,
//                System.currentTimeMillis(),
//                event.symbol(),
//                "side=BUY;qty=1;reason=strategySignal"
//        );


//        bus.publish(EventType.ORDER_REQUEST, orderReq);
    }
}
