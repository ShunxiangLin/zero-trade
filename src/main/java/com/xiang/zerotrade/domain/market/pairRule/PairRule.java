package com.xiang.zerotrade.domain.market.pairRule;

/**
 * @author linshunxiang
 */

public record PairRule(
        TradeRule tradeRule,
        FeeRule feeRule,
        ContractRule contractRule
) {

}
