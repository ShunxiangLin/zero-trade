package com.xiang.zerotrade.domain.event;

import com.xiang.zerotrade.domain.enums.EventType;

/**
 * @author linshunxiang
 */
/**
 * 最简事件模型：不可变（record）。
 * P0 先用 String payload，后面再升级成结构化对象/JSON。
 */
public record Event(
        String eventId,
        EventType eventType,
        long ts,         // epoch millis
        String symbol,
        String payload   // P0: "price=65000" 这种就行
) {}
