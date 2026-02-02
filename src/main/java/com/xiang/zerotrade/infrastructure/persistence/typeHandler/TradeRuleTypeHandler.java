package com.xiang.zerotrade.infrastructure.persistence.typeHandler;

import com.xiang.zerotrade.domain.market.pair.TradeRule;

/**
 * @author linshunxiang
 */

public class TradeRuleTypeHandler extends JsonTypeHandler<TradeRule>{
    public TradeRuleTypeHandler() {
        super(TradeRule.class);
    }
}
