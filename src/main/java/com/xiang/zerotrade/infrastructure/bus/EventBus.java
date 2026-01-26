package com.xiang.zerotrade.infrastructure.bus;

import com.xiang.zerotrade.domain.enums.EventType;

import java.util.function.Consumer;

/**
 * @author linshunxiang
 */

public interface EventBus {
    <E> void publish(EventType eventType, E event);

    <E> void subscribe(EventType eventType, String handlerName, Consumer<E> handler);
}
