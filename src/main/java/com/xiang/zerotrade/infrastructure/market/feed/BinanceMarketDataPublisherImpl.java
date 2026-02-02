package com.xiang.zerotrade.infrastructure.market.feed;


import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.kline.Kline;
import com.xiang.zerotrade.domain.market.pair.Pair;
import com.xiang.zerotrade.infrastructure.bus.impl.LocalEventBus;
import com.xiang.zerotrade.infrastructure.market.MarketDataPublisher;
import com.xiang.zerotrade.infrastructure.market.pair.PairCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;


/**
 * @author shunxiang.lin
 * @date 29/01/2026
 */
@Component
@RequiredArgsConstructor
public class BinanceMarketDataPublisherImpl implements MarketDataPublisher {

    private final LocalEventBus bus;
    private final ObjectMapper objectMapper;
    private final PairCache pairCache;

    @Override
    public void publishTick(MarketType marketType, String msg) {
        // 解析msg
        Kline kline = parseMsg(marketType, msg);
        if (kline == null) {
            return;
        }
        System.out.println(kline);


        // 推送Event
        // #TODO

//        bus.publish(event);
    }

    private Kline parseMsg(MarketType marketType, String msg) {
        JsonNode root = null;
        root = objectMapper.readTree(msg);

        JsonNode data = root.path("data").path("k");
        if (!data.path("x").asBoolean()) {
            return null;
        }
        Pair pair = pairCache.getPairBySymbol(data.path('s').asText(), marketType);
        return fromWebSocketJson(pair, data);


    }

    private Kline fromWebSocketJson(Pair pair, JsonNode json) {
        return Kline.builder()
                .pairId(pair.id())
                .openTime(json.path("t").asLong())
                .closeTime(json.path("T").asLong())
                .openPrice(new BigDecimal(json.path("o").asText()))
                .closePrice(new BigDecimal(json.path("c").asText()))
                .highPrice(new BigDecimal(json.path("h").asText()))
                .lowPrice(new BigDecimal(json.path("l").asText()))
                .volume(new BigDecimal(json.path("v").asText()))
                .build();
    }


}
