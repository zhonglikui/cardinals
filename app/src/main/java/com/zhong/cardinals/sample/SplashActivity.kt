package com.zhong.cardinals.sample

import android.content.Intent
import android.os.Bundle
import com.zhong.cardinals.App
import com.zhong.cardinals.base.BaseActivity
import com.zhong.cardinals.sample.login.LoginActivity
import com.zhong.cardinals.util.Logger
import com.zhong.cardinals.util.ToastUtil

class SplashActivity : BaseActivity() {
    private val INTERVAL_TIME = 3 * 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = intent
        val action = intent.action
        if (Intent.ACTION_VIEW == action) {
            val uri = intent.data
            if (uri != null) {
                val host = uri.host
                val scheme = uri.scheme
                val name = uri.getQueryParameter("name")
                ToastUtil.showLong("参数 name：" + name!!)
//                Logger.i("host=$host ; scheme=$scheme ; name=$name")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        App.getInstance().handler.postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, INTERVAL_TIME.toLong())
    }
}
