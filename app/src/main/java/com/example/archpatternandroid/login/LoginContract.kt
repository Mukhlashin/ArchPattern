package com.example.archpatternandroid.login

import com.example.archpatternandroid.base.BaseView

interface LoginContract {

    interface Presenter {
        fun login(name: String, password: String)
    }

    interface View : BaseView{

    }
}