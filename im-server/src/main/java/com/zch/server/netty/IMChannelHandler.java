package com.zch.server.netty;

import com.zch.common.model.IMSendInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Poison02
 * @date 2024/4/13
 */
@Slf4j
public class IMChannelHandler extends SimpleChannelInboundHandler<IMSendInfo> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, IMSendInfo imSendInfo) throws Exception {

    }
}
