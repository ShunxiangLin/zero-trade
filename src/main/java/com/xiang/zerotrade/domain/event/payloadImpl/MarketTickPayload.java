package com.xiang.zerotrade.domain.event.payloadImpl;

import com.xiang.zerotrade.domain.event.EventPayload;

/**
 * @author linshunxiang
 */

public record MarketTickPayload(
        long price
) implements EventPayload {
}
