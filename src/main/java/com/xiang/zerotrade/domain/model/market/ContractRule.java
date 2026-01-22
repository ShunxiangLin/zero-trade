package com.xiang.zerotrade.domain.model.market;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */

public record ContractRule(
        BigDecimal contractSize,
        Integer maxLeverage
) {
}
