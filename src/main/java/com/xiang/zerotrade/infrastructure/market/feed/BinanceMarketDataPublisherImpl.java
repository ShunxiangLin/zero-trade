package com.xiang.zerotrade.infrastructure.market.feed;

import com.xiang.zerotrade.infrastructure.market.MarketDataPublisher;
import org.springframework.stereotype.Component;

/**
 * @author shunxiang.lin
 * @date 29/01/2026
 */
@Component
public class BinanceMarketDataPublisherImpl implements MarketDataPublisher {
    @Override
    public void publishTick(String msg) {
        System.out.println(msg);
        // 解析msg
        // 推送Event
    }


}
