package com.example.archpatternandroid.repository

import com.example.archpatternandroid.entity.ResponseLogin
import retrofit2.Call

interface LoginRepository {
    fun login (name: String, password : String) : Call<ResponseLogin>

}