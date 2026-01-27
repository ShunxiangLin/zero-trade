package com.xiang.zerotrade.domain.event.payloadImpl;

import com.xiang.zerotrade.domain.event.EventPayload;

/**
 * @author linshunxiang
 */

public record OrderRequestPayload(
        String side,
        int qty
) implements EventPayload {
}
