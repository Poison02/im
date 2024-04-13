package com.zch.common.model;

import lombok.Data;

/**
 * @author Poison02
 * @date 2024/4/13
 */
@Data
public class IMSendResult<T> {

    /**
     * 发送方
     */
    private IMUserInfo sender;

    /**
     * 接收方
     */
    private IMUserInfo receiver;

    /**
     * 发送状态 IMCmdType
     */
    private Integer code;

    /**
     * 消息内容
     */
    private T data;

}
