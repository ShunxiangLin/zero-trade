package com.xiang.zerotrade.domain.model.market;

/**
 * @author linshunxiang
 */

public record PairRule(
        TradeRule tradeRule,
        FeeRule feeRule,
        ContractRule contractRule
) {

}
