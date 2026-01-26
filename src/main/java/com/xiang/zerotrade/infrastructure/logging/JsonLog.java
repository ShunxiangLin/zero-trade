package com.xiang.zerotrade.infrastructure.logging;

import lombok.experimental.UtilityClass;

import java.util.Iterator;
import java.util.Map;

/**
 * @author linshunxiang
 */

@UtilityClass
public class JsonLog {

    public static String toJson(Map<String, ?> m) {
        StringBuilder sb = new StringBuilder(256);
        sb.append('{');
        Iterator<? extends Map.Entry<String, ?>> it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ?> e = it.next();
            sb.append('"').append(escape(e.getKey())).append('"').append(':');
            sb.append(valueToJson(e.getValue()));
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append('}');
        return sb.toString();
    }

    private static String valueToJson(Object v) {
        if (v == null) {
            return "null";
        }
        if (v instanceof Number || v instanceof Boolean) {
            return String.valueOf(v);
        }
        return "\"" + escape(String.valueOf(v)) + "\"";
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
