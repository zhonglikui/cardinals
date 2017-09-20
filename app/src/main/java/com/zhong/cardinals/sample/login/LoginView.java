package com.zhong.cardinals.sample.login;

import com.zhong.cardinals.mvp.MvpView;
import com.zhong.cardinals.sample.mode.UserInfo;

/**
 * Created by Mr.zhong on 2017/9/19.
 */

public interface LoginView extends MvpView {
    void isEmptyPhoneNumber();

    void isEmptyPassword();

    void showDialog();

    void closeDialog();

    void onSuccess(UserInfo userInfo);

    void onFaile(int code, String msg);
}
