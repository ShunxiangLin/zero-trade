package com.xiang.zerotrade.infrastructure.market.clent;

import com.xiang.zerotrade.common.until.RestHelper;
import com.xiang.zerotrade.domain.market.kline.Kline;
import com.xiang.zerotrade.domain.market.pair.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.xiang.zerotrade.common.until.BinanceRestEndpoints.baseUrl;
import static com.xiang.zerotrade.common.until.BinanceRestEndpoints.klinePath;

/**
 * @author linshunxiang
 */
@Component
@RequiredArgsConstructor
public class KlineRestClient {

    private final RestHelper restHelper;
    private final ObjectMapper objectMapper;

    // =================================================
    // region === Market 相关请求 ===
    // =================================================
    public List<Kline> fetchKines(Pair pair, Long startTime, Long endTime) {
        return fetchKline(pair, baseUrl(pair.getMarketType()), klinePath(pair.getMarketType()), startTime, endTime);
    }

    private List<Kline> fetchKline(Pair pair, String baseUrl, String path, Long startTime, Long endTime) {
        String uri = path
                     + "?symbol=" + pair.getSymbol()
                     + "&interval=5m"
                     + "&startTime=" + startTime
                     + "&endTime=" + endTime;
        String response = restHelper.get(baseUrl, uri, String.class);
        JsonNode root = null;
        root = objectMapper.readTree(response);
        List<Kline> result = new ArrayList<>();
        for (JsonNode arr : root) {
            result.add(fromArray(arr, pair));
        }
        return result;
    }

    private Kline fromArray(JsonNode arr, Pair pair) {
        return Kline.builder()
                .pairId(pair.getId())
                .openTime(arr.get(0).asLong())
                .closeTime(arr.get(6).asLong())
                .openPrice(new BigDecimal(arr.get(1).asText()))
                .highPrice(new BigDecimal(arr.get(2).asText()))
                .lowPrice(new BigDecimal(arr.get(3).asText()))
                .closePrice(new BigDecimal(arr.get(4).asText()))
                .volume(new BigDecimal(arr.get(5).asText()))
                .build();
    }


}
