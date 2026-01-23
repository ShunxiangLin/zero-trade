package com.xiang.zerotrade.infrastructure.persistence.mapper;

import com.xiang.zerotrade.infrastructure.persistence.po.MarketPairRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author linshunxiang
 */

@Mapper
public interface MarketPairMapper {
    @Select("""
                SELECT
                  mp.id                AS mp_id,
                  mp.market_type       AS mp_market_type,
                  mp.base_asset        AS mp_base_asset,
                  mp.quote_asset       AS mp_quote_asset,
                  mp.symbol            AS mp_symbol,
                  mp.status            AS mp_status,
                  mp.onboard_ts        AS mp_onboard_ts,
                  mp.offline_ts        AS mp_offline_ts,
            
                  pr.id                AS pr_id,
                  pr.tick_size         AS pr_tick_size,
                  pr.step_size         AS pr_step_size,
                  pr.min_qty           AS pr_min_qty,
                  pr.max_qty           AS pr_max_qty,
                  pr.min_notional      AS pr_min_notional,
                  pr.max_notional      AS pr_max_notional,
                  pr.maker_fee_rate    AS pr_maker_fee_rate,
                  pr.taker_fee_rate    AS pr_taker_fee_rate,
                  pr.contract_size     AS pr_contract_size,
                  pr.max_leverage      AS pr_max_leverage,
                  pr.updated_at        AS pr_updated_at
                FROM market_pair mp
                LEFT JOIN pair_rule pr ON pr.market_pair_id = mp.id
                WHERE mp.status = 1
            """)
    List<MarketPairRow> selectAll();
}
