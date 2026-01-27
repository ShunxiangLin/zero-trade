package com.xiang.zerotrade.infrastructure.persistence.mapper;

import com.xiang.zerotrade.domain.market.pair.Pair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author linshunxiang
 */

@Mapper
public interface PairMapper {

    List<Pair> selectAll();
}
