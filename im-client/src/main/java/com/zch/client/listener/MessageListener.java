package com.zch.client.listener;

import com.zch.common.model.IMSendResult;

import java.util.List;

/**
 * @author Poison02
 * @date 2024/4/14
 */
public interface MessageListener<T> {

    void process(List<IMSendResult<T>> result);

}