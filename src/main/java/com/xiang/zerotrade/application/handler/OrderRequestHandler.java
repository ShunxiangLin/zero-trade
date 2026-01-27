package com.xiang.zerotrade.application.handler;

import com.xiang.zerotrade.domain.event.EventType;
import com.xiang.zerotrade.domain.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
