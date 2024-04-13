package com.zch.common.model;

import lombok.Data;

/**
 * @author Poison02
 * @date 2024/4/13
 */
@Data
public class IMSendInfo<T> {

    /**
     * 命令
     */
    private Integer cmd;

    /**
     * 推送消息体
     */
    private T data;

}
