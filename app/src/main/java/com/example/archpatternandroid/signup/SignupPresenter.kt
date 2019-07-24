package com.example.archpatternandroid.signup

import android.content.Intent
import com.example.archpatternandroid.base.BasePresenter
import okhttp3.ResponseBody
import com.example.archpatternandroid.utils.MyFunction
import okhttp3.RequestBody
import okhttp3.Call
import okhttp3.Callback

class SignUpPresenter( var signUpView: SignupContract.View? = null) : BasePresenter<SignupContract.View> {
    override fun onAttach(view: SignupContract.View) {
        signUpView = view
    }

    override fun onDettach() {
        signUpView = null
    }

}