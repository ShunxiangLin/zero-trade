package com.xiang.zerotrade.application.market;

import com.xiang.zerotrade.domain.temp.pair.MarketPair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shunxiang.lin
 * @date 23/01/2026
 */
@Component
public class MarketPairCatalog {

    private final List<MarketPair> marketPairCatalog = new ArrayList<>();

    void register(MarketPair marketPair){
        marketPairCatalog.add(marketPair);
    }



}
