package com.zch.common.enums;

import lombok.AllArgsConstructor;

/**
 * @author Poison02
 * @date 2024/4/13
 */
@AllArgsConstructor
public enum IMSendCode {


    /**
     * 发送成功
     */
    SUCCESS(0, "发送成功"),

    /**
     * 对方当前不在线
     */
    NOT_ONLINE(1, "对方当前不在线"),

    /**
     * 未找到对方的channel
     */
    NOT_FIND_CHANNEL(2, "未找到对方的channel"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(9999, "未知异常");

    private final Integer code;
    private final String desc;

    public Integer code() {
        return this.code;
    }

}
