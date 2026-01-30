package com.xiang.zerotrade.application.bootstrap;

import com.xiang.zerotrade.application.handler.EventHandler;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.infrastructure.bus.impl.LocalEventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author linshunxiang
 */
@Component
@RequiredArgsConstructor
public class EventHandlerRegistrar {

    private final LocalEventBus bus;
    private final List<EventHandler> handlerList;

    /**
     * 开机订阅 所有注册的handler
     */
    public void registerAll() {
        for (EventHandler handler : handlerList) {
            bus.subscribe(handler);
        }
        bus.logSubscriptions();
    }


}
