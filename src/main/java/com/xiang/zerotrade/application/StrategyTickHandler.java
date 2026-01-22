package com.xiang.zerotrade.application;

import com.xiang.zerotrade.common.exception.AppException;
import com.xiang.zerotrade.common.exception.ErrorCode;
import com.xiang.zerotrade.domain.event.EventHandler;
import com.xiang.zerotrade.domain.event.StrategyTickEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
public class StrategyTickHandler implements EventHandler<StrategyTickEvent> {

    @Override
    public void handle(StrategyTickEvent event) {
        log.info("Handle tick: strategyId={}, symbol={}, ts={}",
                event.strategyId(), event.symbol(), event.ts());

        // 模拟三种结果：成功 / 风控拒绝 / 超时（为了验证 ErrorCode 分支）
        int m = Math.floorMod(event.ts().getEpochSecond(), 3);
        if (m == 1) {
            throw new AppException(ErrorCode.RISK_REJECTED, "Risk rule: demo reject");
        }
        if (m == 2) {
            throw new AppException(ErrorCode.REMOTE_TIMEOUT, "Exchange timeout (demo)");
        }

        log.info("Tick processed OK");
    }
}
