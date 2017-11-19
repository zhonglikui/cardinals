package com.zhong.cardinals.base;

import java.util.List;

/**
 * Created by zhong on 2017/2/6.
 */

public class BaseListResponse<T> {
    private int code = -101;
    private String message;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
