package com.zch.server.netty;

/**
 * @author Poison02
 * @date 2024/4/13
 */
public interface IMServer {

    boolean isReady();

    void start();

    void stop();

}
