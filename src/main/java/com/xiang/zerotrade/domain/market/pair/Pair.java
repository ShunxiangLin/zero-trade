package com.xiang.zerotrade.domain.market.pair;

import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.enums.PairStatus;
import lombok.Data;

@Data
public class Pair {
    int id;
    MarketType marketType;
    String symbol;
    String base;
    String quote;
    PairStatus status;
    TradeRule tradeRule;
    FeeRule feeRule;
    ContractRule contractRule;
    Long onboardDate;
    Long deliveryDate;

    public boolean isTradable(){
        return status == PairStatus.TRADING;
    }

    @Override
    public String toString() {
        return new StringBuilder(256)
                .append('{')
                .append("\"id\":").append(id).append(',')
                .append("\"marketType\":\"").append(marketType).append("\",")
                .append("\"symbol\":\"").append(symbol).append("\",")
                .append("\"status\":\"").append(status).append("\",")
                .append("\"tradeRule\":").append(tradeRule).append(',')
                .append("\"feeRule\":").append(feeRule).append(',')
                .append("\"contractRule\":").append(contractRule).append(',')
                .append("\"onboardDate\":").append(onboardDate).append(',')
                .append("\"deliveryDate\":").append(deliveryDate)
                .append('}')
                .toString();
    }
}
