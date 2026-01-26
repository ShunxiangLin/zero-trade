package com.xiang.zerotrade.infrastructure.persistence.assembler;

import com.xiang.zerotrade.domain.temp.enums.MarketType;
import com.xiang.zerotrade.domain.temp.pair.MarketPair;
import com.xiang.zerotrade.domain.temp.pair.Pair;
import com.xiang.zerotrade.domain.temp.pairRule.ContractRule;
import com.xiang.zerotrade.domain.temp.pairRule.FeeRule;
import com.xiang.zerotrade.domain.temp.pairRule.PairRule;
import com.xiang.zerotrade.domain.temp.pairRule.TradeRule;
import com.xiang.zerotrade.infrastructure.persistence.po.MarketPairRow;

/**
 * @author shunxiang.lin
 * @date 23/01/2026
 */
public class MarketPairAssembler {
    public static MarketPair toDomain(MarketPairRow r) {
        Pair pair = new Pair(r.getMpBaseAsset(), r.getMpQuoteAsset());

        TradeRule tradeRule = TradeRule.builder()
                .tickSize(r.getPrTickSize())
                .stepSize(r.getPrStepSize())
                .minPrice(r.getPrMinPrice())
                .minQty(r.getPrMinQty())
                .maxQty(r.getPrMaxQty())
                .minNotional(r.getPrMinNotional())
                .maxNotional(r.getPrMaxNotional())
                .build();

        FeeRule fee = new FeeRule(r.getPrMakerFeeRate(), r.getPrTakerFeeRate());

        ContractRule contract = new ContractRule(r.getPrContractSize());

        PairRule rule = new PairRule(tradeRule, fee, contract);

        return new MarketPair(MarketType.valueOf(r.getMpMarketType()), pair);
    }
}
