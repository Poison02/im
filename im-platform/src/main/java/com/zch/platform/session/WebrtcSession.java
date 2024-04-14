package com.zch.platform.session;

import lombok.Data;

/**
 * @author Poison02
 * @date 2024/4/14
 */
@Data
public class WebrtcSession {
    /**
     * 发起者id
     */
    private Long callerId;
    /**
     * 发起者终端类型
     */
    private Integer callerTerminal;

    /**
     * 接受者id
     */
    private Long acceptorId;

    /**
     * 接受者终端类型
     */
    private Integer acceptorTerminal;
}
