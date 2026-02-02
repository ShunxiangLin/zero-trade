package com.xiang.zerotrade.infrastructure.market.cache;

import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.pair.Pair;
import com.xiang.zerotrade.infrastructure.persistence.mapper.PairMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class PairCache {

    private final PairMapper pairMapper;

    private final List<Pair> registryPairList = new ArrayList<>();

    public Pair getPairBySymbol(String symbol, MarketType type) {
        return registryPairList.stream()
                .filter(pair -> pair.getSymbol().equals(symbol) && pair.getMarketType() == type)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Pair not found in cache: symbol=" + symbol + ", type=" + type
                ));
    }

    public Pair getPairById(Integer pair_id) {
        return registryPairList.stream()
                .filter(pair -> pair.getId() == pair_id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Pair not found in cache: id=" + pair_id));
    }

    // P0：启动加载一次即可
    public void loadAll() {
        registryPairList.addAll(pairMapper.selectAll());
    }

    public List<Pair> getAll() {
        return List.copyOf(registryPairList);
    }

    public void logsPair() {
        log.info("======== 加载Pair ========");
        Map<MarketType, List<Pair>> grouped =
                registryPairList.stream().collect(Collectors.groupingBy(Pair::getMarketType));

        grouped.forEach((type, list) -> {
            log.info("{}", type.name());
            for (Pair pair : list) {
                log.info("  - {}", pair.getSymbol());
            }
        });
        log.info("=======================================");
    }


}
