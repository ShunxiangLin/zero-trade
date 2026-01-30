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
public class OrderRequestHandler implements EventHandler {

    @Override
    public EventType eventType() {
        return EventType.ORDER_REQUEST;
    }

    @Override
    public String name() {
        return "订单处理器";
    }

    @Override
    public void handle(Event event) {

        System.out.println("交易");
    }


}
