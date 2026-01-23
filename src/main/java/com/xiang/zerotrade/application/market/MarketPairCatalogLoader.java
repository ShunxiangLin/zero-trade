package com.xiang.zerotrade.application.market;

import com.xiang.zerotrade.infrastructure.persistence.assembler.MarketPairAssembler;
import com.xiang.zerotrade.infrastructure.persistence.mapper.MarketPairMapper;
import com.xiang.zerotrade.infrastructure.persistence.po.MarketPairRow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shunxiang.lin
 * @date 23/01/2026
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MarketPairCatalogLoader {

    private final MarketPairCatalog marketPairCatalog;
    private final MarketPairMapper mapper;

    public void load() {
        List<MarketPairRow> marketPairRows = mapper.selectAll();

        marketPairRows.stream()
                .map(MarketPairAssembler::toDomain)
                .forEach(marketPairCatalog::register);

        log.info("MarketCatalog loaded: {} market_pairs", marketPairRows.size());
    }

}
