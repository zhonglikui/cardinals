package com.zhong.cardinals.sample

import com.zhong.cardinals.base.BaseResponse
import com.zhong.cardinals.sample.mode.PasswordLogin
import com.zhong.cardinals.sample.mode.UserInfo

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by zhong on 2017/8/2.
 */

interface API {
    /**
     * 密码登录
     *
     * @param info
     * @return
     */
    @POST("/login/password_login")
    fun loginByPassword(@Body info: PasswordLogin): Call<BaseResponse<UserInfo>>
}
