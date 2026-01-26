package com.xiang.zerotrade.domain.temp.pairRule;

/**
 * @author linshunxiang
 */

public record PairRule(
        TradeRule tradeRule,
        FeeRule feeRule,
        ContractRule contractRule
) {

}
