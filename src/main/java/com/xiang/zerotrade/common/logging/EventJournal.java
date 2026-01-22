package com.xiang.zerotrade.common.logging;

/**
 * @author linshunxiang
 */
public interface EventJournal {
    void append(Object event);
}
