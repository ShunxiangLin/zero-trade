package com.xiang.zerotrade.application.bootstrap;

import com.xiang.zerotrade.application.handler.EventHandler;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import com.xiang.zerotrade.infrastructure.bus.impl.LocalEventBus;
import jakarta.annotation.PostConstruct;
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
    private final List<EventHandler<?>> handlers;

    /**
     * 开机订阅 所有注册的handler
     */
    public void registerAll() {
        for (EventHandler<?> h : handlers) {
            bus.subscribe(h.eventType(), h.name(), h::handle);
        }
        bus.logSubscriptions();

    }


}
