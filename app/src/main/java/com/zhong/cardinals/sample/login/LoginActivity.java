package com.zhong.cardinals.sample.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.zhong.cardinals.mvp.MvpActivity;
import com.zhong.cardinals.sample.MainActivity;
import com.zhong.cardinals.sample.R;
import com.zhong.cardinals.sample.mode.UserInfo;
import com.zhong.cardinals.util.ToastUtil;

public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView, View.OnClickListener {
    private AppCompatEditText etPhone;
    private AppCompatEditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPhone = (AppCompatEditText) findViewById(R.id.et_phone);
        etPassword = (AppCompatEditText) findViewById(R.id.et_password);
        findViewById(R.id.bt_login).setOnClickListener(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void isEmptyPhoneNumber() {
        ToastUtil.showShort("请输入手机号码");
    }

    @Override
    public void isEmptyPassword() {
        ToastUtil.showShort("请输入密码");
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        ToastUtil.showShort("登录成功");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFaile(int code, String msg) {
        ToastUtil.showShort(msg);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();
                getPresenter().login(phone, password);
                break;
        }
    }
}
