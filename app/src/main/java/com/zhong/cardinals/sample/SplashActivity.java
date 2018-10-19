package com.zhong.cardinals.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.zhong.cardinals.App;

import com.zhong.cardinals.sample.login.LoginActivity;
import com.zhong.cardinals.util.Logger;
import com.zhong.cardinals.util.ToastUtil;

public class SplashActivity extends Activity {
    private static final int INTERVAL_TIME = 3 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                String host = uri.getHost();
                String scheme = uri.getScheme();
                String name = uri.getQueryParameter("name");
                ToastUtil.showLong("参数 name：" + name);
                Logger.i("host=" + host + " ; scheme=" + scheme + " ; name=" + name);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getInstance().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, INTERVAL_TIME);
    }
}
