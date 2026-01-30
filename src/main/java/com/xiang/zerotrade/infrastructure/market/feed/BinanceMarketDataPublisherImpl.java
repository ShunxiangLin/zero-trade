package com.xiang.zerotrade.infrastructure.market.feed;


import com.xiang.zerotrade.infrastructure.bus.impl.LocalEventBus;
import com.xiang.zerotrade.infrastructure.market.MarketDataPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


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
        // 解析msg
        System.out.println(msg);

        // 推送Event
        // #TODO

//        bus.publish(event);
    }


}
