package com.xiang.zerotrade.common.until;


import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;

import java.net.URI;
import java.util.function.Consumer;

/**
 * @author linshunxiang
 */

public class WebSocketHelper {
    public static void subscribe(String url, Consumer<String> messageHandler) {
        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(URI.create(url), session ->
                session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .doOnNext(messageHandler)
                        .then()
        ).subscribe();
    }

    public static void close(){

    }

}
