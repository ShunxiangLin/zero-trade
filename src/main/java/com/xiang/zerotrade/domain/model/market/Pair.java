package com.xiang.zerotrade.domain.model.market;

/**
 * @param baseAsset  基础币种，例如 BTC
 * @param quoteAsset 报价币种，例如 USDT
 * @author linshunxiang
 */
public record Pair(
        String baseAsset,
        String quoteAsset
) {
    public Pair {
        baseAsset = normalize(baseAsset);
        quoteAsset = normalize(quoteAsset);
        if (baseAsset == null || baseAsset.isBlank()) {
            throw new IllegalArgumentException("baseAsset must not be blank");
        }
        if (quoteAsset == null || quoteAsset.isBlank()) {
            throw new IllegalArgumentException("quoteAsset must not be blank");
        }
    }

    private static String normalize(String v) {
        return v == null ? null : v.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return display();
    }

    public static Pair of(String baseAsset, String quoteAsset) {
        return new Pair(baseAsset, quoteAsset);
    }

    public String display() {
        return baseAsset + "/" + quoteAsset;
    }

    public String symbol() {
        return baseAsset + quoteAsset;
    }
}