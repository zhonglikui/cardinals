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
    private AppCompatEditText etPhone, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.str_login);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        findViewById(R.id.bt_login).setOnClickListener(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void isEmptyPhoneNumber() {
        ToastUtil.showShort(R.string.str_empty_phone_number);
    }

    @Override
    public void isEmptyPassword() {
        ToastUtil.showShort(R.string.str_empty_password);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        ToastUtil.showShort(R.string.str_login_success);
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
