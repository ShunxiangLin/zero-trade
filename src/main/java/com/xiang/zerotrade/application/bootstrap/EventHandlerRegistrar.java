package com.xiang.zerotrade.application.bootstrap;

import com.xiang.zerotrade.application.handler.EventHandler;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
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

    private final EventBus bus;
    private final List<EventHandler<?>> handlers;

    @PostConstruct
    public void registerAll() {
        for (EventHandler<?> h : handlers) {
            register(h);
        }
    }

    @SuppressWarnings("unchecked")
    private <E> void register(EventHandler<?> raw) {
        EventHandler<E> h = (EventHandler<E>) raw;
        bus.subscribe(h.eventType(), h.name(), h::handle);
    }


}
