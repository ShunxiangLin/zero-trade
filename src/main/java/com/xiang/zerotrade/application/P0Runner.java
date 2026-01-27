package com.xiang.zerotrade.application;

import com.xiang.zerotrade.domain.enums.EventType;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import com.xiang.zerotrade.infrastructure.logging.JsonLog;
import com.xiang.zerotrade.infrastructure.logging.LogKeys;
import com.xiang.zerotrade.infrastructure.logging.Logs;
import com.xiang.zerotrade.infrastructure.logging.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

/**
 * @author linshunxiang
 */
@Component
@RequiredArgsConstructor
public class P0Runner implements CommandLineRunner {

    private final EventBus bus;
    private final Random r = new Random();

    @Override
    public void run(String... args) throws Exception {
        String traceId = Trace.newTraceId();

//        Logs.APP.info(JsonLog.toJson(Map.of(
//                LogKeys.TS, System.currentTimeMillis(),
//                LogKeys.SCENE, "STARTUP",
//                LogKeys.TRACE_ID, traceId,
//                LogKeys.MSG, "P0Runner started"
//        )));

        for (int i = 0; i < 5; i++) {
            Event tick = new Event(
                    Trace.newEventId(),
                    EventType.MARKET_TICK,
                    System.currentTimeMillis(),
                    "BTCUSDT",
                    "price=" + (65000 + r.nextInt(2000))
            );

//            Logs.APP.info(JsonLog.toJson(Map.of(
//                    LogKeys.TS, System.currentTimeMillis(),
//                    LogKeys.SCENE, "BUS",
//                    LogKeys.TRACE_ID, traceId,
//                    LogKeys.EVENT_ID, tick.eventId(),
//                    LogKeys.EVENT_TYPE, tick.eventType().name(),
//                    LogKeys.SYMBOL, tick.symbol(),
//                    LogKeys.MSG, "publish tick"
//            )));

            bus.publish(EventType.MARKET_TICK, tick);
            Thread.sleep(1000);
        }
    }
}

