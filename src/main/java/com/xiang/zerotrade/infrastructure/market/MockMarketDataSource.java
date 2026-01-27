package com.xiang.zerotrade.infrastructure.market;

import com.xiang.zerotrade.domain.market.pair.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class MockMarketDataSource implements MarketDataSource, CommandLineRunner {

    private final MarketDataPublisher publisher;
    private volatile boolean running = true;

    @Override
    public void start() {
        new Thread(() -> {
            long price = 65000 + new Random().nextInt(1000);
            while (running) {
//                publisher.publishTick(Pair.of("BTC","USDT"), price++, System.currentTimeMillis());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "mock-market-feed").start();

        log.info("[FEED] MockMarketDataSource started");
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public void run(String... args) throws Exception {
        start();
    }
}
