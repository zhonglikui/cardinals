package com.zhong.cardinals.retrofit;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhong on 2017/3/20.
 */

public abstract class RetrofitCallback<T> implements Callback<T> {
    public abstract void onSuccess(T model);

    public abstract void onFailure(int code, String msg);

    public abstract void onThrowable(Throwable t);

    public abstract void onFinish();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            try {
                onFailure(response.code(), response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        onFinish();

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onThrowable(t);
        onFinish();

    }
}
