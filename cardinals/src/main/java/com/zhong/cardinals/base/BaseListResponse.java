package com.zhong.cardinals.base;

import java.util.List;

/**
 * Created by zhong on 2017/2/6.
 */

public class BaseListResponse<T> extends BaseData {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
