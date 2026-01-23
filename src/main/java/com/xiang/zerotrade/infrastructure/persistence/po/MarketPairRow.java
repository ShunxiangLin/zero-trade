package com.xiang.zerotrade.infrastructure.persistence.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author linshunxiang
 */
@Data
public class MarketPairRow {
    // market_pair
    private Long mpId;
    private String mpMarketType;
    private String mpBaseAsset;
    private String mpQuoteAsset;
    private String mpSymbol;
    private Integer mpStatus;
    private Long mpOnboardTs;
    private Long mpOfflineTs;

    // pair_rule（可能为 null，因为 LEFT JOIN）
    private Long prId;
    private BigDecimal prTickSize;
    private BigDecimal prStepSize;

    private BigDecimal prMinPrice;
    private BigDecimal prMinQty;
    private BigDecimal prMaxQty;
    private BigDecimal prMinNotional;
    private BigDecimal prMaxNotional;

    private BigDecimal prMakerFeeRate;
    private BigDecimal prTakerFeeRate;

    private BigDecimal prContractSize;

    private Long prUpdatedAt;
}
