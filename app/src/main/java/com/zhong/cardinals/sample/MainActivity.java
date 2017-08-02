package com.zhong.cardinals.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhong.cardinals.base.BaseCallback;
import com.zhong.cardinals.base.BaseResponse;
import com.zhong.cardinals.net.NetWorkClient;
import com.zhong.cardinals.sample.mode.PasswordLogin;
import com.zhong.cardinals.sample.mode.UserInfo;
import com.zhong.cardinals.security.Encrypt;
import com.zhong.cardinals.security.EncryptByMD5;

/**
 * cardinals的demo
 */
public class MainActivity extends AppCompatActivity {
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        String phone = "1501029XXXX";
        String password = "123456";
        String countryCode = "+86";
        PasswordLogin passwordLogin = new PasswordLogin();
        Encrypt encrypt = new EncryptByMD5();
        passwordLogin.setPassword(encrypt.encrypt(password));
        passwordLogin.setPhone(phone);
        passwordLogin.setRegionCode(countryCode);
        NetWorkClient.createService(API.class).loginByPassword(passwordLogin).enqueue(new BaseCallback<BaseResponse<UserInfo>>() {
            @Override
            public void onSuccess(BaseResponse<UserInfo> model) {

                tv.setText("登录成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                tv.setText("登录失败：" + code + " ; " + msg);

            }

            @Override
            public void onFinish() {
                //如果有dialog的话可以在这个里面关闭

            }
        });
    }
}
