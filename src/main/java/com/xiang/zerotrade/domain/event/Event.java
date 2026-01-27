package com.xiang.zerotrade.domain.event;

/**
 * @author linshunxiang
 */

import com.xiang.zerotrade.domain.market.pair.Pair;

/**
 * 最简事件模型：不可变（record）。
 * P0 先用 String payload，后面再升级成结构化对象/JSON。
 */
public record Event(
        String eventId,
        EventType eventType,
        long ts,         // epoch millis
        Pair pair,
        EventPayload payload   // P0: "price=65000" 这种就行
) {}
