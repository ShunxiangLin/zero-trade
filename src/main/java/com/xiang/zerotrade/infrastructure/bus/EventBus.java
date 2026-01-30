package com.xiang.zerotrade.infrastructure.bus;

import com.xiang.zerotrade.application.handler.EventHandler;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.domain.event.EventType;
import com.xiang.zerotrade.infrastructure.bus.impl.LocalEventBus;
import com.xiang.zerotrade.infrastructure.logging.Logs;

import java.util.function.Consumer;

/**
 * @author linshunxiang
 */
public interface EventBus {

    /**
     * 向本地事件总线推送一个时间。
     */
    void publish(Event event);

    /**
     * 向本地事件总线注册一个事件处理器。
     */
    void subscribe(EventHandler Handler);

}
