package com.xiang.zerotrade.application.handler;

import com.xiang.zerotrade.domain.enums.EventType;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import com.xiang.zerotrade.infrastructure.logging.JsonLog;
import com.xiang.zerotrade.infrastructure.logging.Logs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author linshunxiang
 */

@Component
@RequiredArgsConstructor
public class OrderRequestHandler implements EventHandler<Event> {

    @Override
    public EventType eventType() {
        return EventType.ORDER_REQUEST;
    }

    @Override
    public String name() {
        return "OrderRequestHandler:handler";
    }

    @Override
    public void handle(Event event) {

        System.out.println("交易");
    }


}
