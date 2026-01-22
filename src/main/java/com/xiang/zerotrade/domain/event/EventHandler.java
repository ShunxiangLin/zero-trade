package com.xiang.zerotrade.domain.event;

/**
 * @author linshunxiang
 */

public interface EventHandler<E extends DomainEvent> {
    void handle(E event);
}
