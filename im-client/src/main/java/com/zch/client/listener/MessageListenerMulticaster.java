package com.zch.client.listener;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.zch.client.annotation.IMListener;
import com.zch.common.enums.IMListenerType;
import com.zch.common.model.IMSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * @author Poison02
 * @date 2024/4/14
 */
@Component
public class MessageListenerMulticaster {

    @Autowired(required = false)
    private List<MessageListener> messageListeners = Collections.emptyList();

    public void multicast(IMListenerType listenerType, List<IMSendResult> results) {
        if (CollUtil.isEmpty(results)) {
            return;
        }
        for (MessageListener listener : messageListeners) {
            IMListener annotation = listener.getClass().getAnnotation(IMListener.class);
            if (annotation != null && (annotation.type().equals(IMListenerType.ALL) || annotation.type().equals(listenerType))) {
                results.forEach(result -> {
                    // 将data转回对象类型
                    if (result.getData() instanceof JSONObject) {
                        Type superClass = listener.getClass().getGenericInterfaces()[0];
                        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
                        JSONObject data = (JSONObject) result.getData();
                        result.setData(data.toJavaObject(type));
                    }
                });
                // 回调到调用方处理
                listener.process(results);
            }
        }
    }

}
