package com.xiang.zerotrade.infrastructure.persistence.mapper;

import com.xiang.zerotrade.domain.market.kline.Kline;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author linshunxiang
 */

@Mapper
public interface KlineMapper {

    void upsert(List<Kline> kline);
}
