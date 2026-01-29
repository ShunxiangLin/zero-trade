package com.xiang.zerotrade.infrastructure.bus.impl;

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
import java.util.function.Consumer;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
public class LocalEventBus implements EventBus {

    private final Map<EventType, List<Registration>> handlers = new ConcurrentHashMap<>();

    private record Registration(String name, Consumer<Object> consumer) {
    }

    @Override
    public <E> void publish(EventType type, E event) {
        List<Registration> list = handlers.get(type);
        if (list == null || list.isEmpty()) return;

        for (Registration reg : list) {
            long t0 = System.nanoTime();
            try {
                reg.consumer().accept(event);

//                long latencyMs = (System.nanoTime() - t0) / 1_000_000;
//                // 成功日志可先关（debug），避免太吵
//                Logs.METRICS.info(JsonLog.toJson(Map.of(
//                        LogKeys.TS, System.currentTimeMillis(),
//                        LogKeys.SCENE, "BUS",
//                        LogKeys.EVENT_TYPE, type.name(),
//                        LogKeys.HANDLER, reg.name(),
//                        LogKeys.OUTCOME, "SUCCESS",
//                        LogKeys.LATENCY_MS, latencyMs,
//                        LogKeys.MSG, "消息已处理"
//                )));
            } catch (Exception ex) {
                long latencyMs = (System.nanoTime() - t0) / 1_000_000;

                Map<String, Object> m = new LinkedHashMap<>();
                m.put(LogKeys.TS, System.currentTimeMillis());
                m.put(LogKeys.SCENE, "BUS");
                m.put(LogKeys.EVENT_TYPE, type.name());
                m.put(LogKeys.HANDLER, reg.name());
                m.put(LogKeys.OUTCOME, "FAIL");
                m.put(LogKeys.LATENCY_MS, latencyMs);
                m.put("error", ex.getClass().getSimpleName());
                m.put("errorMsg", safeMsg(ex.getMessage()));
                m.put(LogKeys.MSG, "handler failed but event bus continued");

                Logs.APP.error(JsonLog.toJson(m));
                // 关键：不抛出，继续下一个 handler
            }
        }
    }

    @Override
    public <E> void subscribe(EventType type, String handlerName, Consumer<E> handler) {
        @SuppressWarnings("unchecked")
        Consumer<Object> boxed = (Consumer<Object>) handler;

        handlers
                .computeIfAbsent(type, __ -> new CopyOnWriteArrayList<>())
                .add(new Registration(handlerName, boxed));
        log.info("【EVENT订阅初始化】{} 成功注册 {} 事件", handlerName, type.name());
    }


    private static String safeMsg(String msg) {
        if (msg == null) {
            return "";
        }
        return msg.length() > 200 ? msg.substring(0, 200) : msg;
    }

    public void logSubscriptions() {
        log.info("======== EventBus Subscriptions ========");

        handlers.forEach((eventType, regs) -> {
            log.info("{}", eventType.name());
            for (Registration reg : regs) {
                log.info("  - {}", reg.name());
            }
        });
        log.info("=======================================");
    }
}
