package com.zch.common.model;

import lombok.Data;

/**
 * @author Poison02
 * @date 2024/4/13
 */
@Data
public class IMSessionInfo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 终端类型
     */
    private Integer terminal;

}
