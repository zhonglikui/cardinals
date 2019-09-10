package com.zhong.cardinals.sample.login

import com.zhong.cardinals.mvp.MvpView
import com.zhong.cardinals.sample.mode.UserInfo

/**
 * Created by Mr.zhong on 2017/9/19.
 */

interface LoginView : MvpView {
    fun isEmptyPhoneNumber()

    fun isEmptyPassword()

    fun showDialog()

    fun closeDialog()

    fun onSuccess(userInfo: UserInfo?)

    fun onFaile(code: Int, msg: String)
}
