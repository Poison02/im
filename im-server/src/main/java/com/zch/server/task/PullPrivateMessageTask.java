package com.zch.server.task;

import com.alibaba.fastjson.JSONObject;
import com.zch.common.constants.IMRedisKey;
import com.zch.common.enums.IMCmdType;
import com.zch.common.model.IMRecvInfo;
import com.zch.server.netty.IMServerGroup;
import com.zch.server.netty.processor.AbstractMessageProcessor;
import com.zch.server.netty.processor.ProcessorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Poison02
 * @date 2024/4/14
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PullPrivateMessageTask extends AbstractPullMessageTask{

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void pullMessage() {
        // 从redis拉取未读消息
        String key = String.join(":", IMRedisKey.IM_MESSAGE_PRIVATE_QUEUE, IMServerGroup.serverId + "");
        JSONObject jsonObject = (JSONObject) redisTemplate.opsForList().leftPop(key);
        while (!Objects.isNull(jsonObject)) {
            IMRecvInfo recvInfo = jsonObject.toJavaObject(IMRecvInfo.class);
            AbstractMessageProcessor processor = ProcessorFactory.createProcessor(IMCmdType.PRIVATE_MESSAGE);
            processor.process(recvInfo);
            // 下一条消息
            jsonObject = (JSONObject) redisTemplate.opsForList().leftPop(key);
        }
    }

}
