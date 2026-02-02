package com.xiang.zerotrade.common.until;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author linshunxiang
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class RestHelper {

    private final WebClient.Builder binanceWebClient;

    public <T> T get(String baseUrl, String uri, Class<T> clazz) {
        return binanceWebClient.baseUrl(baseUrl)
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(10 * 1024 * 1024)).build()
                .get()
                .uri(uri)
                .retrieve()
                .onStatus(
                        HttpStatusCode -> !HttpStatusCode.is2xxSuccessful(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(body -> {
                                    log.error("⬅️❌ Error Response {}", body);
                                    return Mono.error(new RuntimeException("请求失败: " + body));
                                })
                )
                .bodyToMono(clazz)
                .block();
    }
}
