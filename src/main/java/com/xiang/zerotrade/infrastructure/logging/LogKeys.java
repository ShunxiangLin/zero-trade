package com.xiang.zerotrade.infrastructure.logging;

import lombok.experimental.UtilityClass;

/**
 * @author linshunxiang
 */

@UtilityClass
public class LogKeys {
    public static final String TS = "ts";
    public static final String TRACE_ID = "traceId";
    public static final String EVENT_ID = "eventId";
    public static final String EVENT_TYPE = "eventType";
    public static final String STAGE = "stage";
    public static final String SCENE = "scene";
    public static final String MSG = "msg";

    public static final String METRIC = "metric";
    public static final String HANDLER = "handler";
    public static final String OUTCOME = "outcome";
    public static final String LATENCY_MS = "latencyMs";
    public static final String ERROR_CODE = "errorCode";
    public static final String SYMBOL = "symbol";
    public static final String STRATEGY = "strategy";
}
