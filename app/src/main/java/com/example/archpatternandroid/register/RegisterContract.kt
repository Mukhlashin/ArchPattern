package com.example.archpatternandroid.register

import com.example.archpatternandroid.base.BaseView

interface RegisterContract {

    interface Presenter {
        fun regis(name: String, email: String, password: String, level: String, file: String)
    }

    interface View : BaseView {
        fun isEmpty()
    }
}