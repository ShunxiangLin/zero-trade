package com.xiang.zerotrade.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author shunxiang.lin
 * @date 30/01/2026
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("sideEffectExecutor")
    public Executor sideEffectExecutor() {
        ThreadPoolTaskExecutor ex = new ThreadPoolTaskExecutor();
        ex.setCorePoolSize(2);
        ex.setMaxPoolSize(4);
        ex.setQueueCapacity(10_000);
        ex.setThreadNamePrefix("sidefx-");
        ex.initialize();
        return ex;
    }
}
