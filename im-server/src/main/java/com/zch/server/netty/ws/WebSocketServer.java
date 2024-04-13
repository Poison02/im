package com.zch.server.netty.ws;

import io.netty.channel.EventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * WebSocket服务器，用于链接网页客户端，协议格式，即IMSendInfo的JSON序列化
 * @author Poison02
 * @date 2024/4/13
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "websocket", value = "enable", havingValue = "true", matchIfMissing = true)
public class WebSocketServer {

    @Value("${websocket.port}")
    private int port;

    private volatile boolean ready = false;

    private EventLoopGroup workerGroup;

    private EventLoopGroup bossGroup;

}
