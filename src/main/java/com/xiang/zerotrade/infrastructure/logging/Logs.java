package com.xiang.zerotrade.infrastructure.logging;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author linshunxiang
 */

@UtilityClass
public class Logs {
    public static final Logger APP = LoggerFactory.getLogger("APP_LOG");
    public static final Logger AUDIT = LoggerFactory.getLogger("EVENT_AUDIT");
    public static final Logger METRICS = LoggerFactory.getLogger("METRICS");
}
