package com.xiang.zerotrade.infrastructure.persistence.typeHandler;

import com.xiang.zerotrade.domain.market.pair.FeeRule;

/**
 * @author linshunxiang
 */

public class FeeRuleTypeHandler extends JsonTypeHandler<FeeRule> {
    public FeeRuleTypeHandler() {
        super(FeeRule.class);
    }
}
