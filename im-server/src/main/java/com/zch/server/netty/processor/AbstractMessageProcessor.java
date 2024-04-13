package com.zch.server.netty.processor;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author Poison02
 * @date 2024/4/13
 */
public abstract class AbstractMessageProcessor<T> {

    public void process(ChannelHandlerContext ctx, T data){}

    public void process(T data){}

    public T transForm(Object o) {
        return (T) o;
    }

}
