package com.xiang.zerotrade.infrastructure.market.pair;

import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.pair.Pair;
import com.xiang.zerotrade.infrastructure.persistence.mapper.PairMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linshunxiang
 */

@Component
@RequiredArgsConstructor
public class PairCache {

    private final PairMapper pairMapper;

    private final List<Pair> registryPairList = new ArrayList<>();

    public Pair getPairBySymbol(String symbol, MarketType type) {
        return registryPairList.stream()
                .filter(pair -> pair.symbol().equals(symbol) && pair.marketType() == type)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Pair not found in cache: symbol=" + symbol + ", type=" + type
                ));
    }

    // P0：启动加载一次即可
    public void loadAll(){
        registryPairList.addAll(pairMapper.selectAll());
    }


}
