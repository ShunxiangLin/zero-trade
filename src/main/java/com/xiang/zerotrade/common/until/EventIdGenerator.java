package com.xiang.zerotrade.common.until;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author linshunxiang
 */

@Component
public class EventIdGenerator {

    private final AtomicLong sequence = new AtomicLong();

    public String nextId() {
        return System.currentTimeMillis() + "-" + sequence.incrementAndGet();
    }
}
