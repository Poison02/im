package com.zch.platform.result;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String message;

    private T data;

}
