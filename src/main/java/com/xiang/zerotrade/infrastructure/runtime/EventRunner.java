package com.xiang.zerotrade.infrastructure.runtime;

import com.xiang.zerotrade.common.exception.AppException;
import com.xiang.zerotrade.common.exception.ErrorCode;
import com.xiang.zerotrade.domain.event.DomainEvent;
import com.xiang.zerotrade.domain.event.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
public class EventRunner {
    public <E extends DomainEvent> void run(E event, EventHandler<E> handler) {
        try {
            handler.handle(event);

        } catch (AppException e) {
            log.warn("Event failed: type={}, code={}, msg={}",
                    event.type(), e.errorCode().code(), e.getMessage(), e);

            // P0 规则（先简单明确）
            if (e.errorCode() == ErrorCode.RISK_REJECTED) {
                // 业务预期失败：跳过本次
                return;
            }
            if (e.errorCode() == ErrorCode.REMOTE_TIMEOUT || e.errorCode() == ErrorCode.RATE_LIMITED) {
                // P0：先不做复杂重试，打日志 + 跳过（后面再加 backoff/retry）
                return;
            }

            // 未分类/内部错误：让它冒泡（你可以改成 stopStrategy）
            throw e;

        } catch (Exception e) {
            log.error("BUG: unhandled exception, eventType={}", event.type(), e);
            throw e;
        }
    }
}
