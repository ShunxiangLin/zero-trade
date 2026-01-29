package com.xiang.zerotrade.domain.event.payloadImpl;

import com.xiang.zerotrade.domain.event.EventPayload;
import com.xiang.zerotrade.domain.market.kline.Kline;

/**
 * @author linshunxiang
 */

public record MarketTickPayload(
        Kline kline
) implements EventPayload {
}
