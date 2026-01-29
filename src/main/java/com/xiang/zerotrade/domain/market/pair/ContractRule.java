package com.xiang.zerotrade.domain.market.pair;

import java.math.BigDecimal;

/**
 * @param contractSize 每份合约大小
 * @author linshunxiang
 */
public record ContractRule(
        BigDecimal contractSize
) {
    @Override
    public String toString() {
        return new StringBuilder(256)
                .append('{')
                .append("\"contractSize\":").append(contractSize)
                .append('}')
                .toString();
    }
}

