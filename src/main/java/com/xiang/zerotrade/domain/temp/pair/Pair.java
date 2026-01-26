package com.xiang.zerotrade.domain.temp.pair;

/**
 * @param baseAsset  基础币种，例如 BTC
 * @param quoteAsset 报价币种，例如 USDT
 * @author linshunxiang
 */
public record Pair(
        String baseAsset,
        String quoteAsset
) {
}