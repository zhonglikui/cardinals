package com.zhong.cardinals.base;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhong on 2017/3/20.
 */

public abstract class BaseCallback<T> implements Callback<T> {
    public abstract void onBefore();

    public abstract void onSuccess(T model);

    public abstract void onFailure(int code, String msg);

    public abstract void onFinish();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {//请求成功
            onSuccess(response.body());
        } else {//请求失败比如 404 500 之类的
            try {
                int code = response.code();
                String error = response.errorBody().string();
                onFailure(code, error);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        onFinish();

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {//没有网络或者数据模型出错之类的
        onFailure(-1, t.getMessage());
        onFinish();

    }
}
