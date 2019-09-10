package com.zhong.cardinals.sample.login

import android.text.TextUtils

import com.zhong.cardinals.mvp.MvpPresenter

/**
 * Created by Mr.zhong on 2017/9/19.
 */
//P层需要同时持有V层和M层的
class LoginPresenter : MvpPresenter<LoginView>() {
    fun login(phoneNumber: String, password: String) {
        if (TextUtils.isEmpty(phoneNumber)) {
            view.isEmptyPhoneNumber()
        } else if (TextUtils.isEmpty(password)) {
            view.isEmptyPassword()
        } else {
            view.onSuccess(null)

            /*PasswordLogin passwordLogin = new PasswordLogin();
            Encrypt encrypt = new EncryptByMD5();
            passwordLogin.setPassword(encrypt.encrypt(password));
            passwordLogin.setPhone(phoneNumber);
            passwordLogin.setRegionCode("+86");
            NetWorkClient.createService(API.class).loginByPassword(passwordLogin).enqueue(new BaseCallback<BaseResponse<UserInfo>>() {
                @Override
                public void onBefore() {
                    getView().showDialog();
                }

                @Override
                public void onSuccess(BaseResponse<UserInfo> model) {
                    final int code = model.getRetcode();
                    switch (code) {
                        case 0:
                            getView().onSuccess(model.getData());
                            break;
                        case -1:
                            getView().onFaile(code, "用户不存在");
                            break;
                        case -2:
                            getView().onFaile(code, "账号或密码错误");
                            break;
                    }


                }

                @Override
                public void onFailure(int code, String msg) {
                    getView().onFaile(code, "网络错误");
                    getView().onSuccess(null);
                }

                @Override
                public void onFinish() {
                    getView().closeDialog();
                    getView().onSuccess(null);
                }
            });*/
        }
    }
}
