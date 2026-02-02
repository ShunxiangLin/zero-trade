package com.xiang.zerotrade.infrastructure.bus.impl;

import com.xiang.zerotrade.application.handler.EventHandler;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.domain.event.EventType;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import com.xiang.zerotrade.infrastructure.logging.JsonLog;
import com.xiang.zerotrade.infrastructure.logging.LogKeys;
import com.xiang.zerotrade.infrastructure.logging.Logs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
public class LocalEventBus implements EventBus {

    private final Map<EventType, List<EventHandler>> handleList = new ConcurrentHashMap<>();

    /**
     * 向本地事件总线推送一个时间。
     */
    @Override
    public void publish(Event event) {
        // 获取订阅此EVENT的所有处理器
        List<EventHandler> list = handleList.get(event.eventType());
        if (list == null || list.isEmpty()) return;

        for (EventHandler handler : list) {
            try {
                handler.handle(event);
            } catch (Exception ex) {
                log.error("{}处理异常", handler.name(), ex);
            }
        }
    }


    /**
     * 向本地事件总线注册一个事件处理器。
     */
    @Override
    public void subscribe(EventHandler eventHandler) {
        List<EventHandler> list = handleList.computeIfAbsent(eventHandler.eventType(), __ -> new CopyOnWriteArrayList<>());

        // 幂等保证：同一 EventType 下只允许存在一个同名 Handler
        list.removeIf(h -> h.name().equals(eventHandler.name()));
        list.add(eventHandler);
    }


    /**
     * 打印启动日志
     */
    public void logSubscriptions() {
        log.info("======== EventBus Subscriptions ========");
        handleList.forEach((eventType, regs) -> {
            log.info("{}", eventType.name());
            for (EventHandler reg : regs) {
                log.info("  - {}", reg.name());
            }
        });
        log.info("=======================================");
    }
}
