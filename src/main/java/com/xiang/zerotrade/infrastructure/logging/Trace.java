package com.xiang.zerotrade.infrastructure.logging;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * @author linshunxiang
 */
@UtilityClass
public class Trace {
    public static String newTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String newEventId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

