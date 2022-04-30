package com.kinrin500.util;

import lombok.Data;

@Data
public class Result<T> {

    private int Code;
    private String Msg;
    private T data;

}
