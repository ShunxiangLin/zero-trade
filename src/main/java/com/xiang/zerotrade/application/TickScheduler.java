package com.xiang.zerotrade.application;

import com.xiang.zerotrade.domain.event.StrategyTickEvent;
import com.xiang.zerotrade.infrastructure.runtime.EventRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author linshunxiang
 */


@Component
public class TickScheduler {

    private final EventRunner runner;
    private final StrategyTickHandler handler;

    public TickScheduler(EventRunner runner, StrategyTickHandler handler) {
        this.runner = runner;
        this.handler = handler;
    }

    @Scheduled(fixedRate = 1000)
    public void tick() {
        var evt = new StrategyTickEvent("SMA_V1", "BTCUSDT", Instant.now());
        runner.run(evt, handler);
    }
}
