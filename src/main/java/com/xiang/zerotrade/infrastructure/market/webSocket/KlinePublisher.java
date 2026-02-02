package com.xiang.zerotrade.infrastructure.market.webSocket;


import com.xiang.zerotrade.domain.event.Event;
import com.xiang.zerotrade.domain.event.EventType;
import com.xiang.zerotrade.domain.event.payloadImpl.MarketTickPayload;
import com.xiang.zerotrade.domain.market.enums.Interval;
import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.kline.Kline;
import com.xiang.zerotrade.domain.market.pair.Pair;
import com.xiang.zerotrade.infrastructure.bus.impl.LocalEventBus;
import com.xiang.zerotrade.infrastructure.market.cache.PairCache;
import com.xiang.zerotrade.common.until.EventIdGenerator;
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
public class KlinePublisher {

    private final LocalEventBus bus;
    private final ObjectMapper objectMapper;
    private final PairCache pairCache;
    private final EventIdGenerator eventIdGenerator;

    public void publishTick(MarketType marketType, String msg) {
        // 解析msg
        Kline kline = parseMsg(marketType, msg);
        if (kline == null) {
            return;
        }
        bus.publish(new Event(eventIdGenerator.nextId(), EventType.MARKET_TICK, System.currentTimeMillis(), kline.pairId(), new MarketTickPayload(kline)));
    }

    private Kline parseMsg(MarketType marketType, String msg) {
        JsonNode root = null;
        root = objectMapper.readTree(msg);

        JsonNode data = root.path("data").path("k");
        if (!data.path("x").asBoolean()) {
            return null;
        }

        Pair pair = pairCache.getPairBySymbol(root.path("data").path("s").asText(), marketType);
        return fromWebSocketJson(pair, data);
    }

    private Kline fromWebSocketJson(Pair pair, JsonNode json) {
        return Kline.builder()
                .pairId(pair.getId())
                .interval(Interval.M1)
                .openTime(json.path("t").asLong())
                .closeTime(json.path("T").asLong())
                .openPrice(json.path("o").decimalValue())
                .closePrice(new BigDecimal(json.path("c").asText()))
                .highPrice(new BigDecimal(json.path("h").asText()))
                .lowPrice(new BigDecimal(json.path("l").asText()))
                .volume(new BigDecimal(json.path("v").asText()))
                .build();
    }


}
