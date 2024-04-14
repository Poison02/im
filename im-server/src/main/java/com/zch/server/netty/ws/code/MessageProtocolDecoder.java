package com.zch.server.netty.ws.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zch.common.model.IMSendInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * @author Poison02
 * @date 2024/4/14
 */
public class MessageProtocolDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, List<Object> list) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        IMSendInfo sendInfo = objectMapper.readValue(textWebSocketFrame.text(), IMSendInfo.class);
        list.add(sendInfo);
    }

}
