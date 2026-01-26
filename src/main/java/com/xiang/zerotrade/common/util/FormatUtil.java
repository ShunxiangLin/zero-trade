package com.xiang.zerotrade.common.util;

/**
 * @author linshunxiang
 */

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author linshunxiang
 */
public class FormatUtil {

    private static final DateTimeFormatter UTC_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV").withZone(ZoneId.of("UTC"));
    private static final DateTimeFormatter DEFAULT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV").withZone(ZoneId.systemDefault());

    // ===============时间显示=================

    /**
     * 将时间戳格式化为【系统默认时区】的时间字符串
     * 注意：同一个时间戳，在不同时区机器上显示结果会不同
     */
    public static String formatTimestampWithZone(long timestampMillis) {
        return DEFAULT_FORMATTER.format(Instant.ofEpochMilli(timestampMillis));
    }

    /**
     * 将时间戳格式化为【UTC 时区】的时间字符串
     * 常用于日志、存储、对账等需要统一时间标准的场景
     */
    public static String formatTimestamp(long timestampMillis) {
        return UTC_FORMATTER.format(Instant.ofEpochMilli(timestampMillis));
    }


    // ===============百分比显示=================

    /**
     * 将小数或比例格式化为百分比字符串
     *
     * @param value          小数形式，例如 0.1234
     * @param fractionDigits 保留小数位数，例如 2 → "12.34%"
     * @return 百分比字符串，例如 "12.34%"
     */
    public static String formatPercent(double value, int fractionDigits) {
        StringBuilder pattern = new StringBuilder("0");
        if (fractionDigits > 0) {
            pattern.append(".");
            pattern.append("0".repeat(fractionDigits));
        }
        pattern.append("%");

        DecimalFormat df = new DecimalFormat(pattern.toString());
        return df.format(value);
    }

    /**
     * 默认保留2位小数的百分比
     */
    public static String formatPercent(double value) {
        return formatPercent(value, 2);
    }

    /**
     * 默认保留2位小数的百分比
     */
    public static String formatPercent(BigDecimal value) {
        return formatPercent(value.doubleValue(), 2);
    }

}