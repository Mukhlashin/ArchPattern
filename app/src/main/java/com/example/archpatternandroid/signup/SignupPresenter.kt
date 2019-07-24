package com.example.archpatternandroid.signup

import android.app.Service
import android.content.Intent
import com.example.archpatternandroid.base.BasePresenter
import com.example.archpatternandroid.register.RegisterContract
import com.example.archpatternandroid.repository.RegisterRepositoryImpl
import okhttp3.ResponseBody
import okhttp3.RequestBody
import okhttp3.Call
import okhttp3.Callback

 class SignupPresenter(var regisView: SignupContract.View? = null) :
    BasePresenter<SignupContract.View> {
    override fun onAttach(view: SignupContract.View) {
        regisView = view
    }

    override fun onDettach() {
        regisView = null
    }

}