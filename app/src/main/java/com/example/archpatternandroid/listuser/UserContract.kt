package com.example.archpatternandroid.listuser

import com.example.archpatternandroid.base.BaseView

interface UserContract {

    interface Presenter {
        fun getListUser()
    }

    interface View : BaseView{

    }
}