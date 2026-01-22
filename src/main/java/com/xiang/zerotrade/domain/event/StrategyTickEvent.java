package com.xiang.zerotrade.domain.event;

import java.time.Instant;

/**
 * @author linshunxiang
 */

public record StrategyTickEvent(
        String strategyId,
        String symbol,
        Instant ts
) implements DomainEvent {
    @Override
    public String type() {
        return "STRATEGY_TICK";
    }
}