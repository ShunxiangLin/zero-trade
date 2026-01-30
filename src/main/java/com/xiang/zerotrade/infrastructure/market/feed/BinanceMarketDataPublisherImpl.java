package com.xiang.zerotrade.infrastructure.market.feed;

import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.domain.event.EventPayload;
import com.xiang.zerotrade.domain.event.EventType;
import com.xiang.zerotrade.domain.event.payloadImpl.MarketTickPayload;
import com.xiang.zerotrade.domain.market.kline.Kline;
import com.xiang.zerotrade.infrastructure.bus.impl.LocalEventBus;
import com.xiang.zerotrade.infrastructure.market.MarketDataPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author shunxiang.lin
 * @date 29/01/2026
 */
@Component
@RequiredArgsConstructor
public class BinanceMarketDataPublisherImpl implements MarketDataPublisher {

    private final LocalEventBus bus;

    @Override
    public void publishTick(String msg) {
//        System.out.println(msg);
        // 解析msg
        // 推送Event
        Event event = new Event("1111", EventType.MARKET_TICK, 2L, 2,
                new MarketTickPayload(new Kline(1L, 3L, 3L, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE)));

        bus.publish(event);


    }


}
