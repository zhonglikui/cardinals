package com.zhong.cardinals.sample.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.zhong.cardinals.mvp.MvpActivity
import com.zhong.cardinals.sample.MainActivity
import com.zhong.cardinals.sample.R
import com.zhong.cardinals.sample.mode.UserInfo
import com.zhong.cardinals.util.ToastUtil

class LoginActivity : MvpActivity<LoginView, LoginPresenter>(), LoginView, View.OnClickListener {
    private var etPhone: AppCompatEditText? = null
    private var etPassword: AppCompatEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.str_login)
        etPhone = findViewById(R.id.et_phone)
        etPassword = findViewById(R.id.et_password)
        findViewById<View>(R.id.bt_login).setOnClickListener(this)
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }


    override fun isEmptyPhoneNumber() {
        ToastUtil.showShort(R.string.str_empty_phone_number)
    }

    override fun isEmptyPassword() {
        ToastUtil.showShort(R.string.str_empty_password)
    }

    override fun showDialog() {

    }

    override fun closeDialog() {

    }

    override fun onSuccess(userInfo: UserInfo?) {
        ToastUtil.showShort(R.string.str_login_success)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onFaile(code: Int, msg: String) {
        ToastUtil.showShort(msg)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_login -> {
                val phone = etPhone!!.text.toString()
                val password = etPassword!!.text.toString()
                presenter.login(phone, password)
            }
        }
    }
}
