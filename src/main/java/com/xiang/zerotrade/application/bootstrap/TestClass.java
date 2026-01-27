package com.xiang.zerotrade.application.bootstrap;

import com.xiang.zerotrade.domain.market.pair.Pair;
import com.xiang.zerotrade.infrastructure.persistence.mapper.PairMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class TestClass implements CommandLineRunner {

    private final PairMapper pairMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("========== PairMapper Smoke Test START ==========");

        List<Pair> pairs = pairMapper.selectAll();

        log.info("pair count={}", pairs.size());
        for (Pair p : pairs) {
            log.info("pair => {}", p);
        }

        log.info("========== PairMapper Smoke Test END ==========");

    }
}
