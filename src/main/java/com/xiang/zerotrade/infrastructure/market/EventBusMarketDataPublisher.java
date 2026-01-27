package com.xiang.zerotrade.infrastructure.market;

import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.domain.event.EventType;
import com.xiang.zerotrade.domain.event.payloadImpl.MarketTickPayload;
import com.xiang.zerotrade.domain.market.pair.Pair;
import com.xiang.zerotrade.infrastructure.bus.EventBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class EventBusMarketDataPublisher implements MarketDataPublisher {

    private final EventBus eventBus;

    @Override
    public void publishTick(Pair pair, long price, long ts) {
        Event event = new Event(
                "1111111",
                EventType.MARKET_TICK,
                ts,
                pair,
                new MarketTickPayload(price)
        );
        log.info("[FEED] publish MARKET_TICK symbol={} price={}", pair, price);
        eventBus.publish(EventType.MARKET_TICK, event);
    }
}
