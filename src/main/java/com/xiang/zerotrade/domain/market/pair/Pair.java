package com.xiang.zerotrade.domain.market.pair;

import com.xiang.zerotrade.domain.market.enums.MarketType;
import com.xiang.zerotrade.domain.market.enums.PairStatus;

/**
 * @param id           数据库主键，仅用于持久化与追踪，不参与业务语义
 * @param marketType   市场类型，例如：SPOT / UM_FUTURE / CM_FUTURE
 * @param symbol       交易所唯一标识的交易对代码，如 BTCUSDT、BTCUSD_251226
 * @param base         基础资产（交易标的），如 BTC、ETH
 * @param quote        计价资产，如 USDT、USD
 * @param status       交易对状态（是否启用、下线等）
 * @param tradeRule    交易规则定义，包含最小数量、步长、价格精度、最小名义金额等
 * @param contractRule 合约规则，仅在期货/合约市场下有意义，包含合约类型、交割信息、合约乘数等
 * @param onboardDate  上线时间（epoch millis），可用于过滤尚未生效的合约
 * @param deliveryDate 交割时间（epoch millis），仅对交割合约有效
 * @param feeRule      手续费规则，定义 maker / taker 费率
 * @author linshunxiang
 */
public record Pair(
        int id,
        MarketType marketType,
        String symbol,
        String base,
        String quote,
        PairStatus status,
        TradeRule tradeRule,
        ContractRule contractRule,
        Long onboardDate,
        Long deliveryDate,
        FeeRule feeRule
) {
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
