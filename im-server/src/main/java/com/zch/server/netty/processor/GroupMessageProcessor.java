package com.zch.server.netty.processor;

import cn.hutool.core.util.StrUtil;
import com.zch.common.constants.IMRedisKey;
import com.zch.common.enums.IMCmdType;
import com.zch.common.enums.IMSendCode;
import com.zch.common.model.IMRecvInfo;
import com.zch.common.model.IMSendInfo;
import com.zch.common.model.IMSendResult;
import com.zch.common.model.IMUserInfo;
import com.zch.server.netty.UserChannelCtxMap;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupMessageProcessor extends AbstractMessageProcessor<IMRecvInfo> {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void process(IMRecvInfo recvInfo) {
        IMUserInfo sender = recvInfo.getSender();
        List<IMUserInfo> receivers = recvInfo.getReceivers();
        log.info("接收到群消息，发送者:{},接收用户数量:{}，内容:{}", sender.getId(), receivers.size(), recvInfo.getData());
        for (IMUserInfo receiver : receivers) {
            try {
                ChannelHandlerContext channelCtx = UserChannelCtxMap.getChannelCtx(receiver.getId(), receiver.getTerminal());
                if (channelCtx != null) {
                    // 推送消息到用户
                    IMSendInfo sendInfo = new IMSendInfo();
                    sendInfo.setCmd(IMCmdType.GROUP_MESSAGE.code());
                    sendInfo.setData(recvInfo.getData());
                    channelCtx.channel().writeAndFlush(sendInfo);
                    // 消息发送成功确认
                    sendResult(recvInfo, receiver, IMSendCode.SUCCESS);

                } else {
                    // 消息发送成功确认
                    sendResult(recvInfo, receiver, IMSendCode.NOT_FIND_CHANNEL);
                    log.error("未找到channel,发送者:{},接收id:{}，内容:{}", sender.getId(), receiver.getId(), recvInfo.getData());
                }
            } catch (Exception e) {
                // 消息发送失败确认
                sendResult(recvInfo, receiver, IMSendCode.UNKNOWN_ERROR);
                log.error("发送消息异常,发送者:{},接收id:{}，内容:{}", sender.getId(), receiver.getId(), recvInfo.getData());
            }
        }
    }


    private void sendResult(IMRecvInfo recvInfo, IMUserInfo receiver, IMSendCode sendCode) {
        if (recvInfo.getSendResult()) {
            IMSendResult result = new IMSendResult();
            result.setSender(recvInfo.getSender());
            result.setReceiver(receiver);
            result.setCode(sendCode.code());
            result.setData(recvInfo.getData());
            // 推送到结果队列
            String key = StrUtil.join(":", IMRedisKey.IM_RESULT_GROUP_QUEUE,recvInfo.getServiceName());
            redisTemplate.opsForList().rightPush(key, result);
        }
    }
}
