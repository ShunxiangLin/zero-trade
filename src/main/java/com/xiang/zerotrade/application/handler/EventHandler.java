package com.xiang.zerotrade.application.handler;

import com.xiang.zerotrade.domain.event.EventType;

/**
 * @author linshunxiang
 */

public interface EventHandler<E> {
    /**
     * 订阅的事件类型
     */
    EventType eventType();

    /**
     * 用于哪个日志/metrice
     */
    String name();

    /**
     * 处理逻辑
     */
    void handle(E event);
}
