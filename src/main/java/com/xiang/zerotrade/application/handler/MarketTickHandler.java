package com.xiang.zerotrade.application.handler;

import com.xiang.zerotrade.domain.enums.EventType;
import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import com.xiang.zerotrade.infrastructure.logging.JsonLog;
import com.xiang.zerotrade.infrastructure.logging.LogKeys;
import com.xiang.zerotrade.infrastructure.logging.Logs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author linshunxiang
 */

@Component
@RequiredArgsConstructor
public class MarketTickHandler implements EventHandler<Event> {

    @Override
    public EventType eventType() {
        return EventType.MARKET_TICK;
    }

    @Override
    public String name() {
        return "MarketTickHandler#handle";
    }

    @Override
    public void handle(Event event) {
        Logs.APP.info(JsonLog.toJson(Map.of(
                LogKeys.TS, System.currentTimeMillis(),
                LogKeys.SCENE, "HANDLER",
                LogKeys.EVENT_ID, event.eventId(),
                LogKeys.EVENT_TYPE, event.eventType().name(),
                LogKeys.SYMBOL, event.symbol(),
                LogKeys.MSG, "market tick handled"
        )));
    }


}