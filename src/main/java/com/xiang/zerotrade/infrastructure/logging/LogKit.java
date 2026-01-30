package com.xiang.zerotrade.infrastructure.logging;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author shunxiang.lin
 * @date 30/01/2026
 */
@UtilityClass
public class LogKit {
    private static final Logger BUS = LoggerFactory.getLogger("BUS_LOG");
    private static final Logger HANDLER = LoggerFactory.getLogger("HANDLER_LOG");
    private static final Logger STARTUP = LoggerFactory.getLogger("STARTUP_LOG");

    public static void startup(String action, Map<String, Object> extra) {
        Map<String, Object> m = base("STARTUP", action, null, null);
        if (extra != null) m.putAll(extra);
        STARTUP.info(toJson(m));
    }

    public static void published(Object event, String eventId, String eventType, Map<String, Object> extra) {
        Map<String, Object> m = base("BUS", "PUBLISH", eventId, eventType);
        if (extra != null) m.putAll(extra);
        BUS.info(toJson(m));
    }

    public static void handlerOk(String handlerName, String eventId, String eventType, long latencyMs, Map<String, Object> extra) {
        Map<String, Object> m = base("HANDLER", "OK", eventId, eventType);
        m.put(LogKeys.HANDLER, handlerName);
        m.put(LogKeys.LATENCY_MS, latencyMs);
        if (extra != null) m.putAll(extra);
        HANDLER.info(toJson(m));
    }

    public static void handlerFail(String handlerName, String eventId, String eventType, long latencyMs, Throwable ex, Map<String, Object> extra) {
        Map<String, Object> m = base("HANDLER", "FAIL", eventId, eventType);
        m.put(LogKeys.HANDLER, handlerName);
        m.put(LogKeys.LATENCY_MS, latencyMs);
        m.put(LogKeys.ERR, shortErr(ex));
        if (extra != null) m.putAll(extra);
        HANDLER.error(toJson(m), ex);
    }

    /** 可选：给 MDC 塞 traceId，异步要自己传递（后面我再帮你封装） */
    public static void bindTrace(String traceId) {
        if (traceId != null) MDC.put(LogKeys.TRACE_ID, traceId);
    }

    private static Map<String, Object> base(String scene, String outcome, String eventId, String eventType) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put(LogKeys.SCENE, scene);
        m.put(LogKeys.OUTCOME, outcome);
        if (eventId != null) m.put(LogKeys.EVENT_ID, eventId);
        if (eventType != null) m.put(LogKeys.EVENT_TYPE, eventType);
        String traceId = MDC.get(LogKeys.TRACE_ID);
        if (traceId != null) m.put(LogKeys.TRACE_ID, traceId);
        return m;
    }

    private static String shortErr(Throwable ex) {
        if (ex == null) return null;
        String msg = ex.getMessage();
        if (msg == null) msg = "";
        if (msg.length() > 160) msg = msg.substring(0, 160);
        return ex.getClass().getSimpleName() + ":" + msg;
    }

    // 极简 JSON（只处理基本类型/字符串/数字/布尔/null）
    private static String toJson(Map<String, Object> m) {
        StringBuilder sb = new StringBuilder(256);
        sb.append('{');
        boolean first = true;
        for (Map.Entry<String, Object> e : m.entrySet()) {
            if (!first) sb.append(',');
            first = false;
            sb.append('"').append(escape(e.getKey())).append('"').append(':');
            Object v = e.getValue();
            if (v == null) sb.append("null");
            else if (v instanceof Number || v instanceof Boolean) sb.append(v);
            else sb.append('"').append(escape(String.valueOf(v))).append('"');
        }
        sb.append('}');
        return sb.toString();
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}
